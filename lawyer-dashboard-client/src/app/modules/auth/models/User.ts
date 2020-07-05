export interface Authority {
    authority: string;
}

export interface User {
    username: string;
    token?: string;
    authorities?: Authority[];
}
