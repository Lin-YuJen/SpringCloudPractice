package com.enix.common.json

import com.google.gson.JsonElement
import com.google.gson.JsonNull
import kotlin.reflect.full.createInstance

class ResponseJson() {

    constructor(code: Int) : this() {
        this.code = code
    }

    var code: Int = 999
    var message: String = "Unknown"
    var data: JsonElement = JsonNull.INSTANCE

    inline fun <reified T : JsonElement> addData(dataAction: T.() -> Unit): ResponseJson {
        data = T::class.createInstance().apply(dataAction)
        return this
    }

    fun setStatus(codeMessage: CodeMessage): ResponseJson {
        this.code = codeMessage.code
        this.message = codeMessage.message
        return this
    }
}

sealed class CodeMessage(
    val code: Int,
    val message: String
) {
    object SUCCESS : CodeMessage(200, "成功")
    object INSERT_FAILURE : CodeMessage(444, "新增失敗")
    object FAILURE : CodeMessage(998, "失敗")
    object UNKNOWN : CodeMessage(999, "未知")
    object DATE_TIME_PARSE_FAILURE : CodeMessage(1001, "日期時間轉換錯誤")
}