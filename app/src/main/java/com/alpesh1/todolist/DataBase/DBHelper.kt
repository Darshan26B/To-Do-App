package com.alpesh1.todolist.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.alpesh1.todolist.ModelClass.TaskModel

class DBHelper(context: Context?) : SQLiteOpenHelper(context, "Database", null, 1) {

    var TABLE_NAME = "Task"
    var ID = "id"
    var ADDTASK = "addtask"
    var DATE = "date"
    var Time = "time"


    override fun onCreate(p0: SQLiteDatabase?) {

        var que = "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT,$ADDTASK TEXT,$DATE DATE,$Time TIME)"

        p0?.execSQL(que)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addTask(taskModel: TaskModel){

        var db = writableDatabase
        var values = ContentValues().apply {
            taskModel.apply {
                put(ADDTASK,addtask)
                put(DATE,date)
                put(Time,time)

            }
        }
        db.insert(TABLE_NAME,null,values)
    }

    fun gettask(): ArrayList<TaskModel> {
        var tasklist = ArrayList<TaskModel>()
        var db = readableDatabase
        var que = "SELECT * FROM $TABLE_NAME"
        var cursor = db.rawQuery(que,null)
        cursor.moveToFirst()

        for (i in 0 ..cursor.count-1){
            var id = cursor.getInt(0)
            var addtask = cursor.getString(1)
            var date = cursor.getInt(2)
            var time = cursor.getInt(3)

            var model = TaskModel(id, addtask, date.toString(), time.toString())

            tasklist.add(model)
            cursor.moveToNext()
        }
        return tasklist
    }

    fun updateTask(taskModel: TaskModel){

        var db = writableDatabase
        var values = ContentValues().apply {
            taskModel.apply {
                put(ADDTASK,addtask)
                put(DATE,date)
                put(Time,time)
            }
        }
        db.update(TABLE_NAME,values,"id=${taskModel.id}",null)
    }
    fun deleteTask(id:Int){
        var db = writableDatabase
        db.delete(TABLE_NAME,"id=$id",null)
    }

}