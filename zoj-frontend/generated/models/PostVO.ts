/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

import type { UserVO } from './UserVO';

export type PostVO = {
    content?: string;
    createTime?: string;
    dislikeNum?: number;
    favourNum?: number;
    hasDislike?: boolean;
    hasFavour?: boolean;
    hasThumb?: boolean;
    id?: number;
    tagList?: Array<string>;
    thumbNum?: number;
    title?: string;
    updateTime?: string;
    user?: UserVO;
    userId?: number;
};
