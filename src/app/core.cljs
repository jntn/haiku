(ns app.core
  (:require [reagent.core :as r]
            [app.views :as v]
            [app.state :refer [app-state]]
            [app.events :as events]))

(defn keypress [e]
  (let [key (String.fromCharCode (.-keyCode e))]
    (events/user-typed-key key)))

(defn app []
  [:div {:class ["center" "fade-in"]}
   [v/haiku]])

(defn stop []
  (js/console.log "Stopping..."))

(defn start []
  (js/console.log "Starting...")
  (set! (.-onkeypress js/document) keypress)
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init []
  (start))
