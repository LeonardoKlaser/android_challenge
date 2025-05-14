package br.com.kunden.movies_catalog.database;
import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import java.sql.SQLException;
import java.util.Objects;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

public class CursorHandler implements Cursor {

    private final Cursor cursor;

    public CursorHandler(Cursor cursor){
        this.cursor = cursor;
    }

    public Cursor getCursor(){
        return this.cursor;
    }

    public String getString(String columnName) throws SQLException {
        int columnIndex = this.cursor.getColumnIndex(columnName);

        if(columnIndex == -1){
            throw new SQLException("Coluna " + columnName + " não existe ou não possui alias no SELECT.");
        }

        String result = this.cursor.getString(columnIndex);
        return Objects.equals(result, "null") ? null : result;
    }

    public Double getDouble(String columnName) throws SQLException {
        int columnIndex = this.cursor.getColumnIndex(columnName);

        if(columnIndex == -1){
            throw new SQLException("Coluna " + columnName + " não existe ou não possui alias no SELECT.");
        }

        return this.cursor.getDouble(columnIndex);
    }

    public Integer getInt(String columnName) throws SQLException {
        int columnIndex = this.cursor.getColumnIndex(columnName);

        if(columnIndex == -1){
            throw new SQLException("Coluna " + columnName + " não existe ou não possui alias no SELECT.");
        }

        return this.cursor.getInt(columnIndex);
    }

    public byte[] getBlob(String columnName) throws SQLException {
        int columnIndex = this.cursor.getColumnIndex(columnName);

        if(columnIndex == -1){
            throw new SQLException("Coluna " + columnName + " não existe ou não possui alias no SELECT.");
        }

        return this.cursor.getBlob(columnIndex);
    }

    public short getShort(String columnName) throws SQLException {
        int columnIndex = this.cursor.getColumnIndex(columnName);

        if(columnIndex == -1){
            throw new SQLException("Coluna " + columnName + " não existe ou não possui alias no SELECT.");
        }

        return this.cursor.getShort(columnIndex);
    }

    public long getLong(String columnName) throws SQLException {
        int columnIndex = this.cursor.getColumnIndex(columnName);

        if(columnIndex == -1){
            throw new SQLException("Coluna " + columnName + " não existe ou não possui alias no SELECT.");
        }

        return this.cursor.getLong(columnIndex);
    }

    public float getFloat(String columnName) throws SQLException {
        int columnIndex = this.cursor.getColumnIndex(columnName);

        if(columnIndex == -1){
            throw new SQLException("Coluna " + columnName + " não existe ou não possui alias no SELECT.");
        }

        return this.cursor.getFloat(columnIndex);
    }

    @Override
    public int getCount() {
        return this.cursor.getCount();
    }

    @Override
    public int getPosition() {
        return this.cursor.getPosition();
    }

    @Override
    public boolean move(int i) {
        return this.cursor.move(i);
    }

    @Override
    public boolean moveToPosition(int i) {
        return this.cursor.moveToPosition(i);
    }

    @Override
    public boolean moveToFirst() {
        return this.cursor.moveToFirst();
    }

    @Override
    public boolean moveToLast() {
        return this.cursor.moveToLast();
    }

    @Override
    public boolean moveToNext() {
        return this.cursor.moveToNext();
    }

    @Override
    public boolean moveToPrevious() {
        return this.cursor.moveToPrevious();
    }

    @Override
    public boolean isFirst() {
        return this.cursor.isFirst();
    }

    @Override
    public boolean isLast() {
        return this.cursor.isLast();
    }

    @Override
    public boolean isBeforeFirst() {
        return this.cursor.isBeforeFirst();
    }

    @Override
    public boolean isAfterLast() {
        return this.cursor.isAfterLast();
    }

    @Override
    public int getColumnIndex(String s) {
        return this.cursor.getColumnIndex(s);
    }

    @Override
    public int getColumnIndexOrThrow(String s) throws IllegalArgumentException {
        return this.cursor.getColumnIndexOrThrow(s);
    }

    @Override
    public String getColumnName(int i) {
        return this.cursor.getColumnName(i);
    }

    @Override
    public String[] getColumnNames() {
        return this.cursor.getColumnNames();
    }

    @Override
    public int getColumnCount() {
        return this.cursor.getColumnCount();
    }

    @Override
    public byte[] getBlob(int i) {
        return this.cursor.getBlob(i);
    }

    @Override
    public String getString(int i) {
        return this.cursor.getString(i);
    }

    @Override
    public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {
        this.cursor.copyStringToBuffer(i, charArrayBuffer);
    }

    @Override
    public short getShort(int i) {
        return this.cursor.getShort(i);
    }

    @Override
    public int getInt(int i) {
        return this.cursor.getInt(i);
    }

    @Override
    public long getLong(int i) {
        return this.cursor.getLong(i);
    }

    @Override
    public float getFloat(int i) {
        return this.cursor.getFloat(i);
    }

    @Override
    public double getDouble(int i) {
        return this.cursor.getDouble(i);
    }

    @Override
    public int getType(int i) {
        return this.cursor.getType(i);
    }

    @Override
    public boolean isNull(int i) {
        return this.cursor.isNull(i);
    }

    @Override
    public void deactivate() {
        this.cursor.deactivate();
    }

    @Override
    public boolean requery() {
        return this.cursor.requery();
    }

    @Override
    public void close() {
        this.cursor.close();
    }

    @Override
    public boolean isClosed() {
        return this.cursor.isClosed();
    }

    @Override
    public void registerContentObserver(ContentObserver contentObserver) {
        this.cursor.registerContentObserver(contentObserver);
    }

    @Override
    public void unregisterContentObserver(ContentObserver contentObserver) {
        this.cursor.unregisterContentObserver(contentObserver);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.cursor.registerDataSetObserver(dataSetObserver);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.cursor.unregisterDataSetObserver(dataSetObserver);
    }

    @Override
    public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
        this.cursor.setNotificationUri(contentResolver, uri);
    }

    @Override
    public Uri getNotificationUri() {
        return this.cursor.getNotificationUri();
    }

    @Override
    public boolean getWantsAllOnMoveCalls() {
        return this.cursor.getWantsAllOnMoveCalls();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void setExtras(Bundle bundle) {
        this.cursor.setExtras(bundle);
    }

    @Override
    public Bundle getExtras() {
        return this.cursor.getExtras();
    }

    @Override
    public Bundle respond(Bundle bundle) {
        return this.cursor.respond(bundle);
    }
}