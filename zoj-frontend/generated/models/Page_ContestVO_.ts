/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

import type { ContestVO } from './ContestVO';
import type { OrderItem } from './OrderItem';

export type Page_ContestVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: Array<OrderItem>;
    pages?: number;
    records?: Array<ContestVO>;
    searchCount?: boolean;
    size?: number;
    total?: number;
};
