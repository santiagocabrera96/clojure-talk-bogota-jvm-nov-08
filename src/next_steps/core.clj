(ns next-steps.core
  (:require [clj.qrgen :refer [as-file from]]))

(clojure.java.shell/sh "open" (str (as-file (from "http://clojurians.net" :size [250 250]))))
(clojure.java.shell/sh "open" (str (as-file (from "https://clojurejobboard.com/" :size [250 250]))))
(clojure.java.shell/sh "open" (str (as-file (from "https://clojure.org/community/companies" :size [250 250]))))
