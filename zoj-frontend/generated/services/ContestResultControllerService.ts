/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponse_boolean_ } from '../models/BaseResponse_boolean_';
import type { BaseResponse_ContestResult_ } from '../models/BaseResponse_ContestResult_';
import type { BaseResponse_ContestResultVO_ } from '../models/BaseResponse_ContestResultVO_';
import type { BaseResponse_List_ContestQuestionAccepted_ } from '../models/BaseResponse_List_ContestQuestionAccepted_';
import type { BaseResponse_List_ContestResultVO_ } from '../models/BaseResponse_List_ContestResultVO_';
import type { BaseResponse_long_ } from '../models/BaseResponse_long_';
import type { BaseResponse_Page_ContestResultVO_ } from '../models/BaseResponse_Page_ContestResultVO_';
import type { BaseResponse_UpdateContestResultVO_ } from '../models/BaseResponse_UpdateContestResultVO_';
import type { ContestResultAddRequest } from '../models/ContestResultAddRequest';
import type { ContestResultQueryRequest } from '../models/ContestResultQueryRequest';
import type { ContestResultSubmitRequest } from '../models/ContestResultSubmitRequest';
import type { ContestResultUpdateRequest } from '../models/ContestResultUpdateRequest';
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
     * getQuestionAcceptedNum
     * @param contestId contestId
     * @returns BaseResponse_List_ContestQuestionAccepted_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static getQuestionAcceptedNumUsingPost(
contestId?: number,
): CancelablePromise<BaseResponse_List_ContestQuestionAccepted_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/contest_result/get/Accepted',
            query: {
                'contestId': contestId,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * getContestResultVOById
     * @param id id
     * @returns BaseResponse_ContestResultVO_ OK
     * @throws ApiError
     */
    public static getContestResultVoByIdUsingGet(
id?: number,
): CancelablePromise<BaseResponse_ContestResultVO_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/contest_result/get/vo',
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
     * listAllContestResultVO
     * @param contestId contestId
     * @returns BaseResponse_List_ContestResultVO_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static listAllContestResultVoUsingPost(
contestId?: number,
): CancelablePromise<BaseResponse_List_ContestResultVO_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/contest_result/list/all/vo',
            query: {
                'contestId': contestId,
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

    /**
     * contestResultSubmit
     * @param contestResultSubmitRequest contestResultSubmitRequest
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static contestResultSubmitUsingPost(
contestResultSubmitRequest: ContestResultSubmitRequest,
): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/contest_result/submit',
            body: contestResultSubmitRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * updateContestResult
     * @param contestResultUpdateRequest contestResultUpdateRequest
     * @returns BaseResponse_UpdateContestResultVO_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static updateContestResultUsingPost(
contestResultUpdateRequest: ContestResultUpdateRequest,
): CancelablePromise<BaseResponse_UpdateContestResultVO_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/contest_result/update',
            body: contestResultUpdateRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

}
