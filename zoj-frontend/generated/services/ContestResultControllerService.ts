/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponse_boolean_ } from '../models/BaseResponse_boolean_';
import type { BaseResponse_ContestResult_ } from '../models/BaseResponse_ContestResult_';
import type { BaseResponse_long_ } from '../models/BaseResponse_long_';
import type { BaseResponse_Page_ContestResultVO_ } from '../models/BaseResponse_Page_ContestResultVO_';
import type { ContestResultAddRequest } from '../models/ContestResultAddRequest';
import type { ContestResultQueryRequest } from '../models/ContestResultQueryRequest';
import type { DeleteRequest } from '../models/DeleteRequest';

import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';

export class ContestResultControllerService {

    /**
     * addContestResult
     * @param contestResultAddRequest contestResultAddRequest
     * @returns BaseResponse_long_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static addContestResultUsingPost(
contestResultAddRequest: ContestResultAddRequest,
): CancelablePromise<BaseResponse_long_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/contest_result/add/',
            body: contestResultAddRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * deleteContestResult
     * @param deleteRequest deleteRequest
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static deleteContestResultUsingPost(
deleteRequest: DeleteRequest,
): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/contest_result/delete',
            body: deleteRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * getContestResultById
     * @param id id
     * @returns BaseResponse_ContestResult_ OK
     * @throws ApiError
     */
    public static getContestResultByIdUsingGet(
id?: number,
): CancelablePromise<BaseResponse_ContestResult_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/contest_result/get',
            query: {
                'id': id,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * listContestResultByPage
     * @param contestResultQueryRequest contestResultQueryRequest
     * @returns BaseResponse_Page_ContestResultVO_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static listContestResultByPageUsingPost(
contestResultQueryRequest: ContestResultQueryRequest,
): CancelablePromise<BaseResponse_Page_ContestResultVO_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/contest_result/list/page',
            body: contestResultQueryRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

}
