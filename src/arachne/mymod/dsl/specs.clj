(ns arachne.tmpl.dsl.specs
  (:require [clojure.spec :as s]
            [arachne.core.dsl.specs :as cspec]))

(s/fdef arachne.tmpl.dsl/create-server
  :args (s/cat :arachne-id ::cspec/id
               :port integer?))

