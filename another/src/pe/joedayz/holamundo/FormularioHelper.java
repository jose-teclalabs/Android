package pe.joedayz.holamundo;

import pe.joedayz.holamundo.modelo.Alumno;
import pe.joedayz.holamundo.modelo.Empleado;
import android.widget.EditText;
import android.widget.RatingBar;

public class FormularioHelper {

	private EditText editNombre;
	private EditText editSite;
	private EditText editTelefono;
	private EditText editDireccion;
	private RatingBar ratingNota;

	public FormularioHelper(Formulario formulario) {

		editNombre = (EditText) formulario.findViewById(R.id.edtNombre);

		editSite = (EditText) formulario.findViewById(R.id.edtSite);
		editTelefono = (EditText) formulario.findViewById(R.id.edtTelefono);
		editDireccion = (EditText) formulario.findViewById(R.id.edtDireccion);

		ratingNota = (RatingBar) formulario.findViewById(R.id.nota);

	}
	
	public FormularioHelper(formularioEmpleado formulario) {

		editNombre = (EditText) formulario.findViewById(R.id.edtNombre);
		editTelefono = (EditText) formulario.findViewById(R.id.edtTelefono);
		editDireccion = (EditText) formulario.findViewById(R.id.edtDireccion);

	}

	public Alumno guardarAlumnoDeFormulario() {

		Alumno alumno = new Alumno();
		alumno.setNombre(editNombre.getText().toString());
		alumno.setSite(editSite.getText().toString());
		alumno.setDireccion(editDireccion.getText().toString());
		alumno.setTelefono(editTelefono.getText().toString());
		alumno.setNota(Double.valueOf(ratingNota.getRating()));

		return alumno;
	}

	public Empleado guardarEmpleadoDeFormulario() {

		Empleado obj = new Empleado();
		obj.setNombre(editNombre.getText().toString());
		obj.setDireccion(editDireccion.getText().toString());
		obj.setTelefono(editTelefono.getText().toString());

		return obj;
	}

	public void colocarAlumnoEnFormulario(Alumno alumnoSeleccionado) {
		// TODO Auto-generated method stub
		editNombre.setText(alumnoSeleccionado.getNombre());
		editSite.setText(alumnoSeleccionado.getSite());
		editDireccion.setText(alumnoSeleccionado.getDireccion());
		editTelefono.setText(alumnoSeleccionado.getTelefono());
		ratingNota.setRating(alumnoSeleccionado.getNota().floatValue());
	}

}
