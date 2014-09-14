package pe.joedayz.holamundo;

import java.io.Serializable;
import java.util.List;

import pe.joedayz.holamundo.dao.AlumnoDAO;
import pe.joedayz.holamundo.modelo.Alumno;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAllumnos extends Activity implements Serializable {
	private Alumno alumno;
	
	private ListView lista;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listade_alumnos);
		

		lista = (ListView) findViewById(R.id.lista);

		registerForContextMenu(lista);

		//cargarLista();

		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
//				alumno = (Alumno) adapter.getItemAtPosition(position);
//				Toast.makeText(ListaAllumnos.this,
//						"Clic en la posicion " + position, Toast.LENGTH_SHORT)
//						.show();
				
				Alumno alumnoSeleccionado = (Alumno) adapter.getItemAtPosition(position);
				
				Intent irParaForumario = new Intent(ListaAllumnos.this,Formulario.class);
				
				irParaForumario.putExtra("alumnoSeleccionado", alumnoSeleccionado);
				
				startActivity(irParaForumario);

			}
		});

		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {

				alumno = (Alumno) adapter.getItemAtPosition(position);
//				Toast.makeText(ListaAllumnos.this,
//						"Clic largo en " + adapter.getItemAtPosition(position),
//						Toast.LENGTH_SHORT).show();

				return false;
			}

		});

	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		cargarLista();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		
		menu.add("Matricular");
		menu.add("Enviar un SMS");
		MenuItem visitar = menu.add("Visitar Sitio Web");
		
		visitar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaSite = new Intent(Intent.ACTION_VIEW);
				Uri localSite = Uri.parse("Http://" + alumno.getSite());
				irParaSite.setData(localSite);
				startActivity(irParaSite);
				return false;
			}
		});
		
		MenuItem llamar = menu.add("Llamar");
		
		llamar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaTelefono = new Intent(Intent.ACTION_CALL);
				Uri llamarA = Uri.parse("tele:" + alumno.getTelefono());
				irParaTelefono.setData(llamarA);
				startActivity(irParaTelefono);
				return false;
				
			}
		});
		
		
		
		
		MenuItem  eliminar = menu.add("Eliminar");
		eliminar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlumnoDAO dao = new AlumnoDAO(ListaAllumnos.this);
				System.out.println("el almuno  es " + alumno);
				dao.eliminar(alumno);
				cargarLista();
				dao.close();
				return false;
			}
		});
		menu.add("Ver en el Mapa");
		menu.add("Enviar un email");
		
		super.onCreateContextMenu(menu, v, menuInfo);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lista_alumnos, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int itemSeleccionado = item.getItemId();

		switch (itemSeleccionado) {
		case R.id.nuevo:
			Intent irParaFormulario = new Intent(this, Formulario.class);
			startActivity(irParaFormulario);
			break;
		case R.id.mapa:

			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}
	
	protected void cargarLista(){
		AlumnoDAO dao = new AlumnoDAO(this);
		List<Alumno> alumnos = dao.getLista();
		dao.close();
		int layout = android.R.layout.simple_list_item_1;
		ArrayAdapter<Alumno> arrayAdapter = new ArrayAdapter<Alumno>(this,
				layout, alumnos);

		lista.setAdapter(arrayAdapter);

	}
}
