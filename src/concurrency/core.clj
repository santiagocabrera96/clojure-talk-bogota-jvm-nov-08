(ns concurrency.core)

; Problema 1:
; Tengo un estado de una cuenta, con saldo, y el historial de movimientos.
; Agregar un movimiento significa agregar al listado de movimientos y actualizar el saldo

(require 'virgil)
(virgil/compile-java ["src"])

; ASÍ se llama Java desde Clojure
(import 'concurrency.Cuenta)
(def cuenta (new Cuenta))
(.hacerMovimiento cuenta 10)
(.hacerMovimiento cuenta -1)

(import 'concurrency.CuentaLenta)
(def cuenta-lenta (new CuentaLenta))
(future (.hacerMovimiento cuenta-lenta 10))
(future (.hacerMovimiento cuenta-lenta -1))

; La concurrencia es dificil si es mutable!

(def m {:saldo     0
        :historial []})
(assoc m :saldo 10)

(def cuenta-clj (atom {:saldo     0
                       :historial []}))
(defn hacer-movimiento! [monto]
  (swap! cuenta-clj
         (fn [cuenta]
           (-> cuenta
               (update :saldo + monto)
               (#(do (Thread/sleep 4000) %))
               (update :historial conj monto)))))

@cuenta-clj
(hacer-movimiento! 10)
(future (hacer-movimiento! -1))
