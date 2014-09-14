package pe.joedayz.holamundo.dao;

import java.util.ArrayList;
import java.util.List;
import pe.joedayz.holamundo.modelo.Empleado;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class EmpleadoDAO extends SQLiteOpenHelper {

	public EmpleadoDAO(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String ddl = "CREATE TABLE Empleado (id INTEGER PRIMARY KEY, " +
				"nombre TEXT UNIQUE NOT NULL," +
				"telefono TEXT, " +
				"direccion TEXT); ";
		db.execSQL(ddl);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String ddl = "DROP TABLE IF EXISTS Empleado";
		db.execSQL(ddl);
		
		this.onCreate(db);
		
	}
	
	public void guardar(Empleado empleado) {
		ContentValues values = new ContentValues();
		values.put("nombre", empleado.getNombre());
		values.put("direccion", empleado.getDireccion());
		values.put("telefono", empleado.getTelefono());
		getWritableDatabase().insert("Empleado", null, values);
		
	}
	
	public List<Empleado> getLista() {
		String[] columnas = {"id", "nombre","telefono", "direccion"};
		Cursor cursor = getWritableDatabase().query("Empleado",
				columnas, null, null, null, null, null);
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		while(cursor.moveToNext()){
			Empleado obj = new Empleado();
			obj.setId(cursor.getLong(0));
			obj.setNombre(cursor.getString(1));
			obj.setTelefono(cursor.getString(2));
			obj.setDireccion(cursor.getString(3));
			empleados.add(obj);
		}
		
		
		return empleados;
	}
	
	public void eliminar(Empleado empleado) {
		// TODO Auto-generated method stub
		String[] args = {empleado.getId().toString()};
		
		getWritableDatabase().delete("Empleado", "id=?", args);
	}
	
}
