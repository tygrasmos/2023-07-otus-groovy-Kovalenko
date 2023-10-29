class MappingSpec {

    def url (@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = UrlSpec) Closure closure) {
        def urlSpec = new UrlSpec()
        def cl= closure.rehydrate(urlSpec, this, this)
        cl.resolveStrategy = Closure.DELEGATE_ONLY
        cl.call()
    }

    def active (@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = ActiveSpec)Closure closure) {
        def activeSpec = new ActiveSpec()
        def cl= closure.rehydrate(activeSpec, this, this)
        cl.resolveStrategy = Closure.DELEGATE_ONLY
        cl.call()
    }
}
