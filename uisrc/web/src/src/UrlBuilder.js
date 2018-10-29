import { stringify } from 'query-string';

export class UrlBuilder {
    apiUrl = ""
    constructor(apiUrl) {
        this.apiUrl = apiUrl;
    }
    buildUrl(resource, params) {
        let query = {}
        if(params.pagination != null) {
            const { page, perPage } = params.pagination;
            query.range = JSON.stringify([
                (page - 1) * perPage,
                page * perPage - 1,
            ])
        }
        if(params.sort != undefined){
            const { field, order } = params.sort;
            query.sort = JSON.stringify([field, order])
        }
        if(params.filter != undefined){
            query.filter = JSON.stringify(params.filter)
        }
        if(params.between != undefined) {
            query.between = JSON.stringify(params.between)
        }

            
        return `${this.apiUrl}/${resource}?${stringify(query)}`;
    }
}