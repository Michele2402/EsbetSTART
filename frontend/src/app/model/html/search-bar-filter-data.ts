export interface SearchBarFilterData {
  nameFilter: string;
  pagination:Pagination;
}

export interface Pagination {
  pageIndex: number;
  pageSize: number;
}
