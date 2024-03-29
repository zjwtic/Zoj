/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */

import type { ContestQuestionData } from './ContestQuestionData';
import type { ContestVO } from './ContestVO';
import type { UserVO } from './UserVO';

export type ContestResultVO = {
    contestId?: number;
    contestQuestionDatas?: Array<ContestQuestionData>;
    contestVO?: ContestVO;
    createTime?: string;
    id?: number;
    submitStatus?: number;
    updateTime?: string;
    userId?: number;
    userVO?: UserVO;
};
