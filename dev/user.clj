(ns user "user namespace for development")

(require '[cemerick.pomegranate :as pomegranate :refer (add-dependencies)])

;; add new dependency in `:coordinates` vector at runtime without restart repl
(let [cl (.getContextClassLoader (Thread/currentThread))]
  (.setContextClassLoader (Thread/currentThread) (clojure.lang.DynamicClassLoader. cl))
  (add-dependencies :coordinates '[
                                   [com.rpl/proxy-plus "0.0.1"]
                                   [cljs-http "0.1.46"]
                                   [metosin/malli "0.2.1"]
                                   [
                                    org.apache.poi/poi-ooxml    "3.9"]
                                   [
                                    org.apache.poi/poi-ooxml-schemas "3.9"]
                                   ;;
                                   ;;
                                   ;;
                                   ;;poi-ooxml requires poi-ooxml-lite. This is a substantially smaller version of the poi-ooxml-full jar (ooxml-schemas-1.4.jar for POI 4.0.0, ooxml-schemas-1.3.jar for POI 3.14 or to POI 3.17, ooxml-schemas-1.1.jar for POI 3.7 up to POI 3.13, ooxml-schemas-1.0.jar for POI 3.5 and 3.6). The larger ooxml-schemas jar is normally only required for development. Similarly, the ooxml-security jar, which contains all of the classes relating to encryption and signing, is normally only required for development. A subset of its contents are in poi-ooxml-schemas. This JAR is ooxml-security-1.1.jar for POI 3.14 onwards and ooxml-security-1.0.jar prior to that.
                                   [org.apache.poi/ooxml-schemas "1.1"]


                                   ;; https://mvnrepository.com/artifact/org.ow2.asm/asm
                                   ;; for jdk 15 asm-9 needed.
                                   [org.ow2.asm/asm "9.0"]

                                   ]
                    :repositories (merge cemerick.pomegranate.aether/maven-central
                                         {"clojars" "https://clojars.org/repo"})))
