/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

import type { Contest } from './Contest';
import type { OrderItem } from './OrderItem';

export type Page_Contest_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: Array<OrderItem>;
    pages?: number;
    records?: Array<Contest>;
    searchCount?: boolean;
    size?: number;
    total?: number;
};
