(ns jntn.app.events
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [jntn.app.state :refer [app-state haiku-verse last-char?]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defn shuffle-haikus []
  (swap! app-state update :haikus shuffle))

(defn handle-keypress [key]
  (let [pos (:position @app-state)
        correct (= (nth (haiku-verse) pos) key)]
    (when (and correct (= 0 (:position @app-state)))
      (swap! app-state assoc :start-time (.getTime (js/Date.))))

    (if correct
      (swap! app-state update-in [:position] inc)
      (swap! app-state assoc-in [:state] :error))

    (when (and correct (= :error (:state @app-state)))
      (swap! app-state assoc-in [:state] :default))

    (when (and correct (last-char?))
      (swap! app-state assoc :state :showing-result :end-time (.getTime (js/Date.))))))

(defn load-haiku-from-server []
  (go (let [response (<! (http/get "/api/haikus"))
            json (.parse js/JSON (:body response))
            haiku (js->clj json :keywordize-keys true)]
        (swap! app-state assoc-in [:haiku] haiku))))

(defn handle-result [key]
  (when (= " " key) ((swap! app-state assoc-in [:state] :default)
                     (swap! app-state assoc-in [:position] 0)
                     (swap! app-state assoc-in [:haiku] nil)
                     (load-haiku-from-server))))

(defn user-typed-key [key]
  (case (:state @app-state)
    :default (handle-keypress key)
    :error (handle-keypress key)
    :showing-result (handle-result key)))
