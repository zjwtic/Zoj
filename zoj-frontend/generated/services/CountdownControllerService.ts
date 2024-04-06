/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponse_long_ } from '../models/BaseResponse_long_';
import type { CountDownBeginRequest } from '../models/CountDownBeginRequest';
import type { CountDownGetRestTimeRequest } from '../models/CountDownGetRestTimeRequest';

import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';

export class CountdownControllerService {

    /**
     * getRemainingTime
     * @param countDownGetRestTimeRequest countDownGetRestTimeRequest
     * @returns BaseResponse_long_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static getRemainingTimeUsingPost(
countDownGetRestTimeRequest: CountDownGetRestTimeRequest,
): CancelablePromise<BaseResponse_long_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/countdown/remaining_time',
            body: countDownGetRestTimeRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * startCountdown
     * @param countDownBeginRequest countDownBeginRequest
     * @returns BaseResponse_long_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static startCountdownUsingPost(
countDownBeginRequest: CountDownBeginRequest,
): CancelablePromise<BaseResponse_long_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/countdown/start',
            body: countDownBeginRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

}
