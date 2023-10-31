package com.enix.provider.controller

import com.enix.common.json.CodeMessage.*
import com.enix.common.json.ResponseJson
import com.enix.common.util.LoggerUtil
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import java.time.format.DateTimeParseException

@ControllerAdvice
class ExceptionHandlerController {

    private val log = LoggerUtil.getLogger<ExceptionHandlerController>()

    /**
     * 時間轉換錯誤
     */
    @ExceptionHandler
    @ResponseBody
    fun dateTimeParseException(exception: DateTimeParseException): String {
        log.error(exception) {
            exception.message
        }
        return ResponseJson().setStatus(DATE_TIME_PARSE_FAILURE).toString()
    }

}