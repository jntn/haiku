(ns jntn.app.core
  (:require [reagent.core :as r]
            [jntn.app.views :as v]
            [jntn.app.events :as events]))

(defn keypress [e]
  (let [key (.fromCharCode js/String (.-keyCode e))]
    (events/user-typed-key key)))

(defn ^:dev/before-load stop []
  (js/console.log "Stopping..."))

(defn ^:dev/after-load start []
  (js/console.log "Starting...")
  (set! (.-onkeypress js/document) keypress)
  (r/render [v/app]
            (.getElementById js/document "app")))

(defn ^:export init []
  (events/load-haiku-from-server)
  (start))
