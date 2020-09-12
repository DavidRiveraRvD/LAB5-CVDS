#Laboratorio 5 - MVC Primefaces Introduction - 2020-2

**********************************************************
----------------------------------------------------------
**********************************************************
# LAB5-CVDS

### Datos básicos
 * **Nombres:** David Fernando Rivera\
				Janer Stiven Vanegas
				
				
**********************************************************
----------------------------------------------------------
**********************************************************
## Parte I. - Jugando a ser un cliente HTTP

1. Abra una terminal Linux o consola de comandos Windows.
2. Realice una conexión síncrona TCP/IP a través de Telnet al siguiente servidor:
	* Host: www.escuelaing.edu.co
	* Puerto: 80
	Teniendo en cuenta los parámetros del comando telnet:
		```telnet HOST PORT```
	
Antes de que el servidor cierre la conexión por falta de comunicación:
Revise la página 36 del RFC del protocolo HTTP, sobre cómo realizar una petición GET. Con esto, solicite al servidor el recurso ‘sssss/abc.html’, usando la versión 1.0 de HTTP.
Asegúrese de presionar ENTER dos veces después de ingresar el comando.
Revise el resultado obtenido. ¿Qué codigo de error sale?, revise el significado del mismo en la lista de códigos de estado HTTP.
¿Qué otros códigos de error existen?, ¿En qué caso se manejarán?
Realice una nueva conexión con telnet, esta vez a:
Host: www.httpbin.org
Puerto: 80
Versión HTTP: 1.1
Ahora, solicite (GET) el recurso /html. ¿Qué se obtiene como resultado?

¡Muy bien!, ¡Acaba de usar del protocolo HTTP sin un navegador Web!. Cada vez que se usa un navegador, éste se conecta a un servidor HTTP, envía peticiones (del protocolo HTTP), espera el resultado de las mismas, y -si se trata de contenido HTML- lo interpreta y dibuja.

Seleccione el contenido HTML de la respuesta y copielo al cortapapeles CTRL-SHIFT-C. Ejecute el comando wc (word count) para contar palabras con la opción -c para contar el número de caracteres:

wc -c 
Pegue el contenido del portapapeles con CTRL-SHIFT-V y presione CTRL-D (fin de archivo de Linux). Si no termina el comando wc presione CTRL-D de nuevo. No presione mas de dos veces CTRL-D indica que se termino la entrada y puede cerrarle la terminal. Debe salir el resultado de la cantidad de caracteres que tiene el contenido HTML que respondió el servidor.

Claro está, las peticiones GET son insuficientes en muchos casos. Investigue: ¿Cuál es la diferencia entre los verbos GET y POST? ¿Qué otros tipos de peticiones existen?

En la practica no se utiliza telnet para hacer peticiones a sitios web sino el comando curl con ayuda de la linea de comandos:

curl www.httpbin.org
Utilice ahora el parámetro -v y con el parámetro -i:

curl -v www.httpbin.org
curl -i www.httpbin.org
¿Cuáles son las diferencias con los diferentes parámetros?











Para este taller se va a trabajar sobre el juego del ahorcado.

El sistema actual de puntuación del juego comienza en 100 puntos y va
descontando 10 puntos cada vez que se propone una letra incorrecta.

Algunos usuarios han propuesto diferentes esquemas para realizar la
puntuación, los cuales se describen a continuación:

* OriginalScore: 
    * Es el esquema actual, se inicia con 100 puntos.
    * No se bonifican las letras correctas.
    * Se penaliza con 10 puntos con cada letra incorrecta.
    * El puntaje minimo es 0.

* BonusScore: 
    * El juego inicia en 0 puntos.
    * Se bonifica con 10 puntos cada letra correcta.
    * Se penaliza con 5 puntos cada letra incorrecta.
    * El puntaje mínimo es 0
    
* PowerBonusScore:
    * El juego inicia en 0 puntos.
    * La $i-ésima$ letra correcta se bonifica con $5^i$.
    * Se penaliza con 8 puntos cada letra incorrecta.
    * El puntaje mínimo es 0
    * Si con las reglas anteriores sobrepasa 500 puntos, el puntaje es
      500.

Lo anterior, se traduce en el siguiente modelo, donde se aplica el
principio de inversión de dependencias:


![](img/model.png)


### Parte I

1. Clone el proyecto (no lo descargue!).
   
2. A partir del código existente, implemente sólo los cascarones del
   modelo antes indicado.

3. Haga la especificación de los métodos calculateScore (de las tres
   variantes de GameScore), a partir de las especificaciones
   generales dadas anteriormente. Recuerde tener en cuenta: @pre,
   @pos, @param, @throws.

4. Haga commit de lo realizado hasta ahora. Desde la terminal:

	```bash		
	git add .			
	git commit -m "especificación métodos"
	```

5. Actualice el archivo `pom.xml` e incluya las dependencias para la ultima versión de JUnit y la versión del compilador de Java a la versión 8 .
   

6. Teniendo en cuenta dichas especificaciones, en la clase donde se
   implementarán las pruebas (GameScoreTest), en los
   comentarios iniciales, especifique las clases de equivalencia para
   las tres variantes de GameScore, e identifique
   condiciones de frontera. 
   
   | Numero | Condiciones frontera | 
   | ------------- | ------------- |
   | OriginalScore|
   | 1 | `puntaje<0` |
   | 2 | `(puntaje%10)!=0`|
   | 3 | `letrasCorrectas<0` |
   | 4 | `letrasIncorrectas<0` |
   | 5 | `letrasIncorrectas>10` |
   | BonusScore |
   | 6 | `puntaje<0` |
   | 7 | `(puntaje%5)!=0` |
   | 8 | `letrasIncorrectas<0` |
   | 9 | `letrasCorrectas<0` |
   | PowerBonusScore |
   | 9 | `puntaje>500`|
   | 10 | `puntaje<0`|
   | 11 | `letrasIncorrectas<0` |
   | 12 | `letrasCorrectas<0` |
   
   ********************************************
   
   | Numero | Clases de equivalencia | Resultado |
   | -------- | --------------------- | ---------- |
   | OriginalScore |
   | 1 | `letrasCorrectas<0 || letrasIncorrectas<0` | `Incorrecto` |
   | 2 | `letrasCorrectas>=0 && letrasIncorrectas=0` | `100` |
   | 3 | `letrasCorrectas>=0 && (letrasIncorrectas>0 && letrasIncorrectas<=10)` | `100-(10*letrasIncorrectas)`|
   | 4 | `letrasCorrectas>0 && letrasIncorrectas>10` | `0` |
   |   |                                             |   |
   | BonusScore |
   | 5 | `letrasCorrectas<0 || letrasIncorrectas<0` | Incorrecto |
   | 6 | `letrasCorrectas>0 && letrasIncorrectas=0` | `letrasCorrectas*10` |
   | 7 | `letrasCorrectas>0 && letrasIncorrectas>0` | `(letrasCorrectas*10)-(letrasIncorrectas*5)` |
   | 8 | `letrasCorrectas>0 && (letrasIncorrectas*2)>=(letrasCorrectas)` | `0` |
   |   |                                             |   |
   | PowerScore |
   | 9 | `letrasCorrectas<0 || letrasIncorrectas<0` | Incorrecto |
   | 10 | `letrasCorrectas>0 && letrasIncorrectas=0` | `5^letrasCorrectas` |
   | 11 | `letrasCorrectas=0 && letrasIncorrectas>0` | `0` |
   | 12 | `letrasCorrectas>=4 && letrasIncorrectas=0` | `500` |
   | 13 |`letrasCorrectas>=1 && letrasIncorrectas>=1` | `(5^letrasCorrectas)-(letrasIncorrectas*8)` |
   
7. Para cada clase de equivalencia y condición de frontera, implemente
   una prueba utilizando JUnit.
   

8. Haga commit de lo realizado hasta ahora. Desde la terminal:

	```bash		
	git add .			
	git commit -m "implementación pruebas"
	```
9. Realice la implementación de los 'cascarones' realizados anteriormente.
   Asegúrese que todas las pruebas unitarias creadas en los puntos anteriores
   se ejecutan satisfactoriamente.

10. Al finalizar haga un nuevo commit:

	```bash		
	git add .			
	git commit -m "implementación del modelo"
	```

11. Para sincronizar el avance en el respositorio y NO PERDER el trabajo, use
    el comando de GIT para enviar los cambios:

```bash	
	git push <URL Repositorio>	
```


### Parte II

Actualmente se utiliza el patrón FactoryMethod
que desacopla la creación de los objetos para diseñar un juego
de ahorcado (revisar createGUIUsingFactoryMethod en SwingProject, el
constructor de la clase GUI y HangmanFactoryMethod).

En este taller se va a utilizar un contenedor liviano ([GoogleGuice](https://github.com/google/guice)) el cual soporta la inyección de las dependencias.

1. Utilizando el HangmanFactoryMethod (MétodoFabrica) incluya el
   OriginalScore a la configuración.

Incorpore el Contenedor Liviano Guice dentro del proyecto:

* Revise las dependencias necesarias en el pom.xml.
* Modifique la inyección de dependencias utilizando guice en lugar del
  método fábrica..
* Configure la aplicación de manera que desde el programa SwingProject
  NO SE CONSTRUYA el Score directamente, sino a través de Guice, asi
  mismo como las otras dependencias que se están inyectando mediante
  la fabrica.
* Mediante la configuración de la Inyección de
  Dependencias se pueda cambiar el comportamiento del mismo, por
  ejemplo:
	* Utilizar el esquema OriginalScore.
	* Utilizar el esquema BonusScore.
	* Utilizar el idioma francés.
    * Utilizar el diccionario francés.
	* etc...
* Para lo anterior, [puede basarse en el ejemplo dado como
  referencia](https://github.com/PDSW-ECI/LightweighContainers_DepenendecyInjectionIntro-WordProcessor).
