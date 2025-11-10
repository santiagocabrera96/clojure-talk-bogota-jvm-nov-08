(ns server.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [hiccup2.core :as h]))

(defn handler [request]
  (def request request)
  (case (:uri request)
    "/html" {:status 200
             :header {"content-type" "text/html"}
             :body   (str (h/html
                            [:h1 "Hola mundo"]
                            [:h2 :headers]
                            [:table
                             (for [[k v] (:headers request)]
                               [:tr [:td k] [:td v]])]))}
    {:status 404
     :body "Not found"}))

(def server (run-jetty #(handler %) {:port 3000 :join? false}))