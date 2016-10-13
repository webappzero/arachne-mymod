(ns arachne.tmpl.validators
  (:require [arachne.core.config :as cfg]
            [arachne.core.config.validation :as v]
            [arachne.core.config.ontology :as ont]
            [arachne.core.util :as util]))

(def illegal-methods #{:bad-method})

(defn method-types
  "Ensure that method types are in the allowed set"
  [cfg]
  (let [methods (cfg/q cfg '[:find ?endpoint ?name ?method
                             :in $
                             :where
                             [?endpoint :arachne.tmpl.endpoint/methods ?method]
                             [?endpoint :arachne.tmpl.endpoint/name ?name]])]
    (for [[endpoint name method] methods]
      (when-not (legal-methods method)
        {::v/message "Unknown HTTP method"
         :method method
         :endpoint endpoint
         :endpoint-name name}))))


(def validators [:arachne.tmpl.validators/method-types])

(defn add-validators
  "Add HTTP validator functions to the config"
  [cfg]
  (let [cfg-eids (cfg/q cfg '[:find [?cfg ...]
                              :where [?cfg :arachne.configuration/roots _]])]
    (cfg/with-provenance :module `add-validators
      (cfg/update cfg (for [c cfg-eids v validators]
                        [:db/add c :arachne.configuration/validators v])))))

