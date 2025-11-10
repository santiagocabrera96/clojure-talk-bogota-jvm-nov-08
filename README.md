# Clojure Talk - Bogotá JVM Noviembre 2024

Este repositorio contiene ejemplos que demuestran la interoperabilidad de Clojure con Java, características de concurrencia, servidores web y más.

## Únete a la Comunidad

**¡Conéctate con la comunidad de Clojure en Slack!** Únete a miles de desarrolladores de Clojure, haz preguntas y comparte tus experiencias:

👉 **[Únete a Clojurians Slack](http://clojurians.net/)**

## Prerrequisitos

- Java (Adoptium Temurin 21 recomendado, o Java 11+)
- [Herramientas CLI de Clojure](https://clojure.org/guides/install_clojure)

Verificar instalación de Java:
```bash
java --version
```

## Instalación de Clojure

### macOS

Prerrequisitos: Java y Homebrew

Instalar usando Homebrew:
```bash
brew install clojure/tools/clojure
```

Para actualizar una instalación existente:
```bash
brew upgrade clojure/tools/clojure
```

### Linux

Prerrequisitos: Java, bash, curl y rlwrap

Descargar y ejecutar el script de instalación de Linux:
```bash
curl -L -O https://github.com/clojure/brew-install/releases/latest/download/linux-install.sh
chmod +x linux-install.sh
sudo ./linux-install.sh
```

Esto crea ejecutables en `/usr/local/bin/clj` y `/usr/local/bin/clojure`.

Para una ubicación de instalación personalizada, usa la bandera `--prefix`:
```bash
sudo ./linux-install.sh --prefix /opt/tu/ruta/personalizada
```

### Windows

Prerrequisitos: Java

#### Opción 1: Windows Subsystem for Linux (WSL) (Recomendado)
Instala WSL, luego sigue las instrucciones de instalación de Linux anteriores.

#### Opción 2: Instalador Nativo de Windows
Usa el instalador [clj-msi](https://github.com/casselc/clj-msi) para instalar `clojure.exe` nativamente en Windows.

### Verificar Instalación

```bash
clj --version
```

## Estructura del Proyecto

```
src/
├── concurrency/     # Ejemplo de concurrencia
│   ├── core.clj
│   ├── Cuenta.java
│   └── CuentaLenta.java
├── interop/         # Ejemplo de interoperabilidad Java-Clojure
│   ├── core.clj
│   ├── Attention.java
│   └── ChangePic.java
├── server/          # Ejemplos de servidor web usando Ring y Jetty
│   ├── core.clj
│   └── JavaServer.java
└── next_steps/      # Generación de códigos QR
    └── core.clj
```

## Ejecutar el Código

### Iniciar un REPL

Para iniciar una sesión interactiva de REPL:

```bash
clj
```

Una vez en el REPL, puedes cargar y experimentar con los diferentes espacios de nombres:

```clojure
;; Cargar los ejemplos de concurrencia
(require '[concurrency.core :as concurrency])

;; Cargar los ejemplos de interoperabilidad
(require '[interop.core :as interop])

;; Cargar los ejemplos de servidor
(require '[server.core :as server])

;; Cargar ejemplos de próximos pasos
(require '[next-steps.core :as next-steps])
```

### Ejecutar Ejemplos Específicos

Desde el REPL, puedes llamar funciones de los espacios de nombres cargados:

```clojure
;; Ejemplo: Iniciar el servidor web (si está disponible)
(server/start-server)

;; Explorar funciones en un espacio de nombres
(dir concurrency.core)
```

### Ejecutar como Script

También puedes ejecutar código Clojure directamente:

```bash
clj -M -m concurrency.core
clj -M -m interop.core
clj -M -m server.core
```

## Experimentar con el REPL

El REPL (Read-Eval-Print Loop) es una de las características más poderosas de Clojure. Aquí algunos consejos:

### Comandos Básicos del REPL

```clojure
;; Evaluar una expresión
(+ 1 2 3)

;; Definir una variable
(def mi-nombre "Clojure")

;; Definir una función
(defn saludar [nombre]
  (str "¡Hola, " nombre "!"))

;; Llamar la función
(saludar mi-nombre)

;; Ver documentación
(doc map)

;; Encontrar funciones
(find-doc "sequence")

;; Ver código fuente
(source map)

;; Listar funciones en un espacio de nombres
(dir clojure.string)
```

### Cargar y Recargar Código

```clojure
;; Cargar un espacio de nombres
(require '[concurrency.core :as cc])

;; Recargar un espacio de nombres (útil cuando has cambiado el código)
(require '[concurrency.core :as cc] :reload)

;; Recargar todas las dependencias
(require '[concurrency.core :as cc] :reload-all)
```

### Trabajar con Interoperabilidad Java

```clojure
;; Crear un objeto Java
(def fecha (java.util.Date.))

;; Llamar métodos Java
(.toString fecha)
(.getTime fecha)

;; Llamar métodos estáticos
(java.lang.Math/sqrt 16)

;; Importar clases Java
(import '[java.util Date ArrayList])
```

### Utilidades Útiles del REPL

```clojure
;; Impresión bonita
(clojure.pprint/pprint {:a 1 :b 2 :c {:d 3}})

;; Inspeccionar datos
(clojure.repl/doc +)
(clojure.repl/source +)

;; Obtener el tipo de algo
(type [1 2 3])
(class {:a 1})
```

## Integración con Editores

Para la mejor experiencia con el REPL, considera usar un editor con soporte para Clojure:

- **VS Code**: Instala [Calva](https://calva.io/)
- **IntelliJ IDEA**: Instala [Cursive](https://cursive-ide.com/)
- **Emacs**: Usa [CIDER](https://cider.mx/)
- **Vim/Neovim**: Prueba [Conjure](https://github.com/Olical/conjure) o [vim-fireplace](https://github.com/tpope/vim-fireplace)

Estas herramientas te permiten evaluar código directamente desde tu editor, resultados en línea y mucho más.

## Dependencias

Este proyecto usa las siguientes dependencias (ver `deps.edn`):

- **Clojure 1.12.3**: El lenguaje principal
- **Virgil**: Para recarga en caliente de código Java
- **clj-java-decompiler**: Para descompilar bytecode Java
- **Ring & Jetty**: Funcionalidad de servidor web
- **Hiccup**: Generación de HTML
- **Charred**: Análisis rápido de JSON
- **data.json**: Codificación/decodificación de JSON
- **clj.qrgen**: Generación de códigos QR

## Recursos de Aprendizaje

- [Documentación Oficial de Clojure](https://clojure.org/)
- [Clojure for the Brave and True](https://www.braveclojure.com/)
- [ClojureDocs](https://clojuredocs.org/)
- [4Clojure](http://www.4clojure.com/) - Problemas interactivos
- [Clojure Koans](http://clojurekoans.com/)

## Licencia

Este es código de ejemplo para propósitos educativos.
