/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

import type { ContestResultVO } from './ContestResultVO';
import type { OrderItem } from './OrderItem';

export type Page_ContestResultVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: Array<OrderItem>;
    pages?: number;
    records?: Array<ContestResultVO>;
    searchCount?: boolean;
    size?: number;
    total?: number;
};
