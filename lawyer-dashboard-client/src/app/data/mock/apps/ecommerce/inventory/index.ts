import { Injectable } from '@angular/core';
import { from } from 'rxjs';
import { map } from 'rxjs/operators';
import * as _ from 'lodash';
import { TreoMockApi } from '@treo/lib/mock-api/mock-api.interfaces';
import { TreoMockApiUtils } from '@treo/lib/mock-api/mock-api.utils';
import { TreoMockApiService } from '@treo/lib/mock-api/mock-api.service';
import { brands as brandsData, categories as categoriesData, products as productsData, tags as tagsData, vendors as vendorsData } from 'app/data/mock/apps/ecommerce/inventory/data';

@Injectable({
    providedIn: 'root'
})
export class ECommerceInventoryMockApi implements TreoMockApi
{
    // Private
    private _categories: any[];
    private _brands: any[];
    private _products: any[];
    private _tags: any[];
    private _vendors: any[];

    /**
     * Constructor
     *
     * @param {TreoMockApiService} _treoMockApiService
     */
    constructor(
        private _treoMockApiService: TreoMockApiService
    )
    {
        // Set the data
        this._categories = categoriesData;
        this._brands = brandsData;
        this._products = productsData;
        this._tags = tagsData;
        this._vendors = vendorsData;

        // Register the API endpoints
        this.register();
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Register
     */
    register(): void
    {
        // -----------------------------------------------------------------------------------------------------
        // @ Categories - GET
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onGet('api/apps/ecommerce/inventory/categories')
            .reply(() => {

                return [
                    200,
                    _.cloneDeep(this._categories)
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Brands - GET
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onGet('api/apps/ecommerce/inventory/brands')
            .reply(() => {

                return [
                    200,
                    _.cloneDeep(this._brands)
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Products - GET
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onGet('api/apps/ecommerce/inventory/products', 625)
            .reply((request) => {

                // Get available queries
                const search = request.params.get('search');
                const sort = request.params.get('sort') || 'name';
                const order = request.params.get('order') || 'asc';
                const page = parseInt(request.params.get('page'), 10);
                const size = parseInt(request.params.get('size'), 10);

                // Clone the products
                let products = _.cloneDeep(this._products);

                // Sort the products
                if ( sort === 'sku' || sort === 'name' || sort === 'active' )
                {
                    products.sort((a, b) => {
                        const fieldA = a[sort].toString().toUpperCase();
                        const fieldB = b[sort].toString().toUpperCase();
                        return order === 'asc' ? fieldA.localeCompare(fieldB) : fieldB.localeCompare(fieldA);
                    });
                }
                else
                {
                    products.sort((a, b) => order === 'asc' ? a[sort] - b[sort] : b[sort] - a[sort]);
                }

                // If search exists...
                if ( search )
                {
                    // Filter the products
                    products = products.filter((contact) => {
                        return contact.name && contact.name.toLowerCase().includes(search.toLowerCase());
                    });
                }

                // Paginate - Start
                const productsLength = products.length;

                // Calculate pagination details
                const begin = page * size;
                const end = Math.min((size * (page + 1)), productsLength);
                const lastPage = Math.max(Math.ceil(productsLength / size), 1);

                // Prepare the pagination object
                let pagination = {};

                // If the requested page number is bigger than
                // the last possible page number, return null for
                // products but also send the last possible page so
                // the app can navigate to there
                if ( page > lastPage )
                {
                    products = null;
                    pagination = {
                        lastPage
                    };
                }
                else
                {
                    // Paginate the results by size
                    products = products.slice(begin, end);

                    // Prepare the pagination data
                    pagination = {
                        length    : productsLength,
                        size      : size,
                        page      : page,
                        lastPage  : lastPage,
                        startIndex: begin,
                        endIndex  : end - 1
                    };
                }
                // Paginate - End

                return [
                    200,
                    {
                        products,
                        pagination
                    }
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Product - GET
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onGet('api/apps/ecommerce/inventory/product')
            .reply((request) => {

                // Get the id from the params
                const id = request.params.get('id');

                // Clone the products
                const products = _.cloneDeep(this._products);

                // Find the product
                const product = products.find((item) => {
                    return item.id === id;
                });

                return [
                    200,
                    product
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Product - PUT
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onPut('api/apps/ecommerce/inventory/product')
            .reply(() => {

                // Generate a new product
                const newProduct = {
                    id         : TreoMockApiUtils.guid(),
                    category   : '',
                    name       : 'A New Product',
                    description: '',
                    tags       : [],
                    sku        : '',
                    barcode    : '',
                    brand      : '',
                    vendor     : '',
                    stock      : '',
                    reserved   : '',
                    cost       : '',
                    basePrice  : '',
                    taxPercent : '',
                    price      : '',
                    weight     : '',
                    thumbnail  : '',
                    images     : [],
                    active     : false
                };

                // Unshift the new product
                this._products.unshift(newProduct);

                return [
                    200,
                    newProduct
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Product - PATCH
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onPatch('api/apps/ecommerce/inventory/product')
            .reply((request) => {

                // Get the id and product
                const id = request.body.id;
                const product = _.cloneDeep(request.body.product);

                // Prepare the updated product
                let updatedProduct = null;

                // Find the product and update it
                this._products.forEach((item, index, products) => {

                    if ( item.id === id )
                    {
                        // Update the product
                        products[index] = _.assign({}, products[index], product);

                        // Store the updated product
                        updatedProduct = products[index];
                    }
                });

                return [
                    200,
                    updatedProduct
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Product - DELETE
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onDelete('api/apps/ecommerce/inventory/product')
            .reply((request) => {

                // Get the id
                const id = request.params.get('id');

                // Find the product and delete it
                this._products.forEach((item, index) => {

                    if ( item.id === id )
                    {
                        this._products.splice(index, 1);
                    }
                });

                return [
                    200,
                    true
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Tags - GET
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onGet('api/apps/ecommerce/inventory/tags')
            .reply(() => {

                return [
                    200,
                    _.cloneDeep(this._tags)
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Tags - PUT
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onPut('api/apps/ecommerce/inventory/tag')
            .reply((request) => {

                // Get the tag
                const newTag = _.cloneDeep(request.body.tag);

                // Generate a new GUID
                newTag.id = TreoMockApiUtils.guid();

                // Unshift the new tag
                this._tags.unshift(newTag);

                return [
                    200,
                    newTag
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Tags - PATCH
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onPatch('api/apps/ecommerce/inventory/tag')
            .reply((request) => {

                // Get the id and tag
                const id = request.body.id;
                const tag = _.cloneDeep(request.body.tag);

                // Prepare the updated tag
                let updatedTag = null;

                // Find the tag and update it
                this._tags.forEach((item, index, tags) => {

                    if ( item.id === id )
                    {
                        // Update the tag
                        tags[index] = _.assign({}, tags[index], tag);

                        // Store the updated tag
                        updatedTag = tags[index];
                    }
                });

                return [
                    200,
                    updatedTag
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Tag - DELETE
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onDelete('api/apps/ecommerce/inventory/tag')
            .reply((request) => {

                // Get the id
                const id = request.params.get('id');

                // Find the tag and delete it
                this._tags.forEach((item, index) => {

                    if ( item.id === id )
                    {
                        this._tags.splice(index, 1);
                    }
                });

                // Get the products that have the tag
                const productsWithTag = this._products.filter(product => product.tags.indexOf(id) > -1);

                // Iterate through them and delete the tag
                productsWithTag.forEach((product) => {
                    product.tags.splice(product.tags.indexOf(id), 1);
                });

                return [
                    200,
                    true
                ];
            });

        // -----------------------------------------------------------------------------------------------------
        // @ Vendors - GET
        // -----------------------------------------------------------------------------------------------------
        this._treoMockApiService
            .onGet('api/apps/ecommerce/inventory/vendors')
            .reply(() => {

                return [
                    200,
                    _.cloneDeep(this._vendors)
                ];
            });
    }
}
