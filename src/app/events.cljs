(ns app.events
  (:require [app.state :refer [app-state]]))

(defn upper
  []
  ;;(println event)
  ;;(.preventDefault event)
  (swap! app-state assoc-in [:hello] "WORLD"))

(defn user-typed-key [key]
  (println key))