package com.almeida.henrique.gym_tracking.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.util.concurrent.ListenableFuture
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Response
import springfox.documentation.service.Tag
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig() {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .tags(
                Tag("Customer", "Customer related operations"),
                Tag("Gym", "Gym related operations")
            )
            .genericModelSubstitutes(ListenableFuture::class.java)
            .useDefaultResponseMessages(false)
            .apiInfo(dataAppInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.almeida.henrique.gym_tracking.resource"))
            .paths(PathSelectors.any())
            .build()
            .globalResponses(HttpMethod.GET, responseMessagesGET())
            .globalResponses(HttpMethod.POST, responseMessagesPOST())
            .globalResponses(HttpMethod.PUT, responseMessagesPUT())
            .globalResponses(HttpMethod.DELETE, responseMessagesDELETE())
    }


    private fun dataAppInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Gym Tracker API")
            .description("REST API for Gym Tracking Web App")
            .version("1.0.0")
            .build()
    }

    private fun responseMessagesGET(): ArrayList<Response> {
        return arrayListOf(
            ResponseBuilder().code("500").description("Internal server error").build(),
            ResponseBuilder().code("404").description("Not found").build(),
            ResponseBuilder().code("400").description("Bad Request").build()
        )
    }

    private fun responseMessagesPOST(): ArrayList<Response> {
        return arrayListOf(
            ResponseBuilder().code("500").description("Internal server error").build(),
            ResponseBuilder().code("404").description("Not found").build(),
            ResponseBuilder().code("400").description("Bad Request").build()
        )
    }


    private fun responseMessagesPUT(): ArrayList<Response> {
        return arrayListOf(
            ResponseBuilder().code("500").description("Internal server error").build(),
            ResponseBuilder().code("404").description("Not found").build(),
            ResponseBuilder().code("400").description("Bad Request").build()
        )
    }

    private fun responseMessagesDELETE(): ArrayList<Response> {
        return arrayListOf(
            ResponseBuilder().code("500").description("Internal server error").build(),
            ResponseBuilder().code("404").description("Not found").build(),
            ResponseBuilder().code("400").description("Bad Request").build()
        )
    }
}
