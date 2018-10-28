import DataProvider from './DataProvider'

export class Config {
    static getDataProvider() {
        return DataProvider('http://localhost:8080')
    }
}