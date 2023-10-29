import org.codehaus.groovy.control.CompilerConfiguration

/** server address
 * http:\\server_addr:server_port\
 * */


ParseFile pr = new ParseFile("dev.conf")
pr.parseFile()

//def config = new ConfigSlurper().parse(new File("config/parent_config.conf").toURI().toURL())
//config.getProperties()
/*CompilerConfiguration configuration = new CompilerConfiguration()
GroovyShell sh = new GroovyShell(this.class.getClassLoader(), new Binding(), configuration)
File scriptFile = new File('config/parent_config.conf')
sh.parse(scriptFile)
sh.getProperties()*/

/*def aaaa (@DelegatesTo(strategy = Closure.DELEGATE_ONLY, value = MappingSpec) Closure closure) {
    def mapSpec = new MappingSpec()
    def cl= closure.rehydrate(mapSpec, this, this)
    cl.resolveStrategy = Closure.DELEGATE_ONLY
    cl.call()
}*/


