(ns co.gaiwan.paged-js
  (:require
   ["pagedjs" :refer [Previewer]]
   ["react-dom/client" :refer [createRoot]]
   ["react" :as react]
   [reagent.core :as r]))

(defonce root (createRoot
               (js/document.getElementById "app")))

(defn book []
  [:div [:h1 "I'm a book"]])

(defn app []
  (react/useLayoutEffect
   (fn []
     (let [previewer (Previewer.)]
       (.preview previewer
                 (.-innerHTML (js/document.querySelector "#app"))
                 #js []
                 (js/document.querySelector "#preview"))))
   #js [])
  [book])
#_
(rdom/render [app]
             (js/document.getElementById "app"))
(defn init
  []
  (.render root (r/as-element [:f> app])))

(defn ^:dev/after-load re-render
  []
  ;; The `:dev/after-load` metadata causes this function to be called
  ;; after shadow-cljs hot-reloads code.
  ;; This function is called implicitly by its annotation.
  (init))

(init)
