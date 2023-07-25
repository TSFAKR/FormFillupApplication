package com.tsfapps.myapplication.utils

import org.json.JSONArray
import org.json.JSONObject

object MergeJsonObject {
    fun mergeJsonObjects(rootObject: JSONObject, jsonObject: JSONObject): JSONObject {
        val merged = JSONObject()
        val objs = arrayOf(rootObject, jsonObject)
        for (obj in objs) {
            val it: Iterator<*> = obj.keys()
            while (it.hasNext()) {
                val key = it.next() as String
                merged.put(key, obj.get(key))
            }
        }
        return merged
    }
    fun mergeJsonObjects3(rootObject: JSONObject, jsonObject: JSONObject, jsonFamily: JSONObject): JSONObject {
        val merged = JSONObject()
        val objs = arrayOf(rootObject, jsonObject, jsonFamily)
        for (obj in objs) {
            val it: Iterator<*> = obj.keys()
            while (it.hasNext()) {
                val key = it.next() as String
                merged.put(key, obj.get(key))
            }
        }
        return merged
    }


    fun mergeJsonObjects(vararg jsonObjects: JSONObject): JSONObject {
        val mergedObject = JSONObject()
        for (jsonObject in jsonObjects) {
            val iterator = jsonObject.keys()
            while (iterator.hasNext()) {
                val key = iterator.next()
                val value = jsonObject.get(key)
                mergedObject.put(key, value)
            }
        }
        return mergedObject
    }
}