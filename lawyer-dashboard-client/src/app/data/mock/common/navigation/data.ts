/* tslint:disable:max-line-length */
import { TreoNavigationItem } from '@treo/components/navigation';

export const defaultNavigation: TreoNavigationItem[] = [
    {
        id      : 'starter',
        title   : 'Lawyer Dashboard',
        type    : 'group',
        icon    : 'apps',
        children: [
            {
                id   : 'search-report-amm',
                title: 'Ricerca Amministrative',
                type : 'basic',
                link : '/customer/search',
                icon : 'heroicons_outline:search',
            },
            {
                id   : 'search-report-patro',
                title: 'Ricerca Patronato',
                type : 'basic',
                link : '/customer/search-patronato',
                icon : 'heroicons_outline:search',
            },
            {
                id   : 'add',
                title: 'Aggiungi Cliente',
                icon : 'heroicons_outline:user-add',
                link : '/customer/add',
                type : 'basic'
            }
            // {
            //     id   : 'import',
            //     title: 'Importa Reports',
            //     icon : 'heroicons_outline:document-report',
            //     type : 'basic',
            //     link : '/customer/report'
            // }
        ]
    }
];
export const compactNavigation: TreoNavigationItem[] = [
    {
        id      : 'starter',
        title   : 'Starter',
        type    : 'aside',
        icon    : 'apps',
        children: [] // This will be filled from defaultNavigation so we don't have to manage multiple sets of the same navigation
    }
];
export const futuristicNavigation: TreoNavigationItem[] = [
    {
        id   : 'starter.example',
        title: 'Example component',
        type : 'basic',
        icon : 'heroicons:chart-pie',
        link : '/example'
    },
    {
        id   : 'starter.dummy.1',
        title: 'Dummy menu item #1',
        icon : 'heroicons:calendar',
        type : 'basic'
    },
    {
        id   : 'starter.dummy.2',
        title: 'Dummy menu item #1',
        icon : 'heroicons:user-group',
        type : 'basic'
    }
];
export const horizontalNavigation: TreoNavigationItem[] = [
    {
        id      : 'starter',
        title   : 'Starter',
        type    : 'group',
        icon    : 'apps',
        children: [] // This will be filled from defaultNavigation so we don't have to manage multiple sets of the same navigation
    }
];
