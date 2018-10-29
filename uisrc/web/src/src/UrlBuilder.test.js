import { UrlBuilder } from "./UrlBuilder";

it("builds url", () => {
    var apiUrl = "http://localhost:8080"
    var params = {
        pagination: {
            page: 1,
            perPage: 10,
        },
        sort: {
            field: "name",
            order: "ASC"
        },
        filter: {
            
        }        
    }
    expect(new UrlBuilder(apiUrl).buildUrl("clients",params)).toBe("http://localhost:8080/clients?filter=%7B%7D&range=%5B0%2C9%5D&sort=%5B%22name%22%2C%22ASC%22%5D")
})