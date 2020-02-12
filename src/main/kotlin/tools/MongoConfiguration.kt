import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Description
import tools.MongoConnect
import tools.MongoConnectImp


/**
 * Configuration class for Spring IoC container
 */
@Configuration
open class MongoConfiguration {
    @Bean
    @Description("Bean text description")
    open fun connect(): MongoConnect {
        return MongoConnectImp()
    }
}