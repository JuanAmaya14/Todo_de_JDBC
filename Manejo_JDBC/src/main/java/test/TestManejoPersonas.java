package test;

import datos.PersonaDAO;
import domain.Persona;

import java.util.List;

public class TestManejoPersonas {

    public static void main(String[] args) {


        PersonaDAO personadao = new PersonaDAO();
        //get
        List<Persona> personas = personadao.Seleccionar();

        for (Persona persona:personas) {

            System.out.println("persona = " + persona);

        }

        //post
        /*Persona newPersona = new Persona("UnRandom", "Ese random", "ElRandom@gmail.com", 4353456);

        personadao.Insertar(newPersona);*/

        //delete
        /*Persona delPersona = new Persona(8);
        personadao.Eliminar(delPersona);*/

        Persona updatePersona = new Persona(5, "si", "SiActializo", "SiAcutalizo@gmail.com", 1111111);
        //personadao.Actualizar(updatePersona);



    }

}
