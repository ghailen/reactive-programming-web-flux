package com.ghailene.Springbootwebfluxdemo.router;

import com.ghailene.Springbootwebfluxdemo.handler.StudentHandler;
import com.ghailene.Springbootwebfluxdemo.handler.StudentStreamHandler;
import com.ghailene.Springbootwebfluxdemo.model.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterImpl {

    private final StudentHandler handler;
    private final StudentStreamHandler streamHandler;

    public RouterImpl(StudentHandler handler, StudentStreamHandler streamHandler) {
        this.handler = handler;
        this.streamHandler = streamHandler;
    }

    @Bean
    @RouterOperations({@RouterOperation(
            path = "/get/students",
            produces ={
            MediaType.APPLICATION_JSON_VALUE
    },
            method = RequestMethod.GET,
            beanClass = StudentHandler.class,
            beanMethod = "getAllStudent",
            operation = @Operation(
                    operationId ="getAllStudent",
                    responses ={ @ApiResponse(
                            responseCode = "200",
                            description = "get all student successfully.",
                            content = @Content(schema = @Schema(
                                    implementation = Student.class
                            ))
                    )}
            )
    ),
            @RouterOperation(
                    path = "/get/students/{student_id}",
                    produces ={
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = StudentHandler.class,
                    beanMethod = "findById",
                    operation = @Operation(
                            operationId ="findById",
                            responses ={ @ApiResponse(
                                    responseCode = "200",
                                    description = "get student by id successfully.",
                                    content = @Content(schema = @Schema(
                                            implementation = Student.class
                                    ))
                            ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "student not found by given id."
                                    )},
                            parameters = @Parameter(in= ParameterIn.PATH,
                            name="student_id")
                    )
            ),
            @RouterOperation(
                    path = "/add/students/{student_id}",
                    produces ={
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.POST,
                    beanClass = StudentHandler.class,
                    beanMethod = "addStudent",
                    operation = @Operation(
                            operationId ="addStudent",
                            responses ={ @ApiResponse(
                                    responseCode = "200",
                                    description = "successful op",
                                    content = @Content(schema = @Schema(
                                            implementation = String.class
                                    ))
                            )},
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(
                                            implementation = Student.class
                                    ))
                            )
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunctionA() {
        return RouterFunctions.route()
                .GET("get/students", handler::getAllStudent)
                .GET("get/students/stream", streamHandler::getAllStudents)
                .GET("get/students/{student_id}", handler::findById)
                .POST("add/students/{student_id}", handler::addStudent)
                .build();
    }



}
