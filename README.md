# Tarea: Alquiler de vehículos (v2)
## Profesor: Andrés Rubio del Río
## Alumno: David Motos Olmedo

Al cliente le ha gustado bastante la aplicación, pero nos comenta algunas mejoras que necesita la anterior versión y nuevas funcionalidades que le gustaría que tuviese. Todo ello lo abordaremos en este **segundo spring**.

Lo primero que nos comenta el cliente es que a la hora de mostrar los datos del turismo, la cilindrada la expresemos en centímetros cúbicos.

También le gustaría que los **listados** lo hiciesen de forma **ordenada**:

- **Clientes**: ordenados alfabéticamente por nombre y en caso de nombres iguales los ordene por DNI.
- **Vehículos**: ordenados alfabéticamente por marca, modelo y matrícula.
- **Alquileres**: ordenados por fecha de alquiler y por cliente (utilizando el mismo criterio anterior).

Por otra parte nos comenta que no sólo alquila **turismos**, sino que también alquila **autobuses** y **furgonetas**. Para los **autobuses** almacena, además de la marca, modelo y matrícula, el **número de plazas** del mismo. Para las **furgonetas almacena**, además de la marca, modelo y matrícula, el **número de plazas** y el **PMA** (peso máximo autorizado).

El precio que cobra por alquiler de un turismo es el siguiente: **(precioDia + factorPrecio) * numDias**. El **precioDia** es **20**, **numDias** son los días transcurridos entre la **fecha de alquiler** y la de **devolución** y el **factorPrecio** depende del tipo de vehículo alquilado:

- **Turismos**: **cilindrada / factorCilindrada**, donde **factorCilindrada** actualmente es **10**.
- **Autobuses**: **plazas * factorPlazas** , donde **factorPlazas** actualmente es **2**.
- **Furgonetas**: **pma / fatorPma + plazas * factorPlazas**, donde **factorPma** es actualmente **100** y **factorPlazas** es actualmente **1**.

Tu tarea consiste en realizar una **segunda versión** de la aplicación anterior para que cubra las mejoras pedidas por el cliente y los nuevos requisitos.

Además vamos a aprovechar este **spring** para **refactorizar** lo que llevamos implementado hasta ahora para implementar de una forma adecuada el **patrón Modelo-Vista-Controlador**, ya que preveemos que tendremos varias vistas y varios modelos para nuestra aplicación. Supondremos que en el futuro podremos tener varios modelos y que cada uno de ellos trabaje con una fuente de datos.También supondremos que en el futuro tendremos varias vistas, así que iremos preparando las cosas para facilitar dicha integración. Implementaremos el **patrón factoría** para crear las vistas o los modelos y la fuente de datos.

Además, te muestro un diagrama de clases para el mismo y poco a poco te iré explicando los diferentes pasos a realizar:

![Diagrama de clases de la tarea](alquilerVehiculos.png)


#### Primeros Pasos
1. Copia tu proyecto actual a una nueva carpeta llamada **AlquilerVehiculos-v1**.

#### Herencia de vehículos
1. Convierte la clase `Turismo` en `Vehiculo`, haciendo que sea una clase abstracta tal y como se indica en el diagrama.
2. Haz que el método `equals` utilice la llamada al método `instanceof` correctamente.
3. Declara el método abstracto `getFactorPrecio`.
4. Implementa la clase `Turismo` como se muestra en el diagrama. Haz uso de `super` siempre que sea posible. Implementa adecuadamente el método `getFactorPrecio` para este vehículo. Haz todos los cambios necesarios para que todo vuelva a funcionar como antes.
5. Implementa la clase `Autobus` como se muestra en el diagrama. Haz uso de `super` siempre que sea posible. Implementa adecuadamente el método `getFactorPrecio` para este vehículo.
6. Implementa la clase `Furgoneta` como se muestra en el diagrama. Haz uso de `super` siempre que sea posible. Implementa adecuadamente el método `getFactorPrecio` para este vehículo.
7. Implementa adecuadamente el método `copiar` de la clase `Vehiculo` que en función del tipo de vehículo devolverá un objeto de tipo `Turismo`, de tipo `Autobus` o de tipo `Furgoneta`.
8. Reimplementa el método `getPrecio` de la clase `Alquiler` para que que calcule el precio del vehículo usando el método getFactorPrecio, es decir, que el precio ahora sería el (PRECIO_DIA+factor precio del vehículo) * número de días del alquiler.
9. Reimplementa el constructor copia del `Alquiler` ya que deberá crearse un vehículo u otro en función de la instancia del vehículo  a copiar.
10. Refactoriza la clase `Turismos` para que pase a llamarse Vehiculos y que utilice siempre la clase `Vehiculo` en vez de `Turismo`.


#### Fuentes de datos para el modelo
1. Extrae de cada clase que contiene una colección su interfaz y haz que dicha clase la implemente (utiliza la refactorización para ello) y a su vez que son utilizadas en `Modelo`.
2. Crea la clase `FuenteDatosMemoria` tal y como se indica en el diagrama que será la encargada de implementar el **patrón fábrica abstracta** y que en cada caso creará una de las clases que contiene una colección. Extrae la interfaz de dicha clase.
3. Crea el enumerado `FactoriaFuenteDatos` que implementa el **patrón método de fabricación**, en este caso para la única fuente de datos que es `MEMORIA`.
4. Convierte la clase `Modelo` en una clase abstracta tal y como se indica en el diagrama y haz que sea implementada por la clase `ModeloCascada`.
5. Has los cambios necesarios en `MainApp` para que todo siga funcionando igual.
6. Comprueba que las **clases pasan los test** para la misma y cuando lo haga realiza un **commit**.


#### Vista de texto
1. Modifica la clase `Vista` para que los diferentes listados salgan ordenados según los criterios que nos pedía el cliente.
2. Refactoriza el enumerado `Opcion` para que pase a llamarse `Accion` que es un nombre más adecuado. Implementa el método `setVista`. Declara el método abstracto ejecutar y haz que sea implementado en cada instancia del enumerado, llamando al método adecuado de la vista.
3. Crea el enumerado `TipoVehiculo` tal y como muestra el diagrama y que modelará el menú para elegir el tipo de vehículo que queremos crear y que también utilizaremos para generar las estadísticas.
4. Modifica la clase `Vista` y la clase `Consola` para que a la hora de insertar podamos elegir el tipo de vehículo y nos permita crear cada uno de los tipos con sus datos necesarios.
5. Haz que la clase `Vista` pase a ser abstracta como se indica en el diagrama y que la anterior clase `Vista` pase a llamarse `VistaTexto` y sea la que implemente dicha clase abstracta y tenga la funcionalidad de la anterior.
6. Haz que `Controlador` utilice dicha clase abstracta.
7. Modifica la clase `MainApp` para que utilice dicho patrón.


#### Se valorará:

- La indentación debe ser correcta en cada uno de los apartados.
- El nombre de las variables debe ser adecuado.
- Se debe utilizar la clase `Entrada` para realizar la entrada por teclado.
- El programa debe pasar todas las pruebas que van en el esqueleto del proyecto y toda entrada del programa será validada, para evitar que el programa termine abruptamente debido a una excepción. Además, que ni decir tiene, el programa no debe contener ningún error léxico, sintáctico, de dependencias, etc.
- La corrección ortográfica tanto en los comentarios como en los mensajes que se muestren al usuario.
