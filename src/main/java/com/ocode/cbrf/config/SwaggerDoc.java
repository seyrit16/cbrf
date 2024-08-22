package com.ocode.cbrf.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Справочник ЦБ РФ",
                version = "1.0",
                description =
                                """
                                Web приложение ведения (добавление/редактирование/удаление
                                записей) справочника кредитно-финансовых учреждений, а также механизм загрузки и
                                обновлений справочника на основе предоставляемых сведений ЦБ РФ, публикуемого по
                                адресу: http://cbr.ru/Psystem/system_p/
                                
                                Авторы:\s
                                -\tЛарин Роман Евгеньевич, ПГУТИ
                                -\tПривалов Матвей Алексеевич, СНИУ (Самарский университет)
                                """,
                contact = @Contact(
                        name = "GitHub:",
                        url = "https://github.com/seyrit16/cbrf"
                )
        )
)
public class SwaggerDoc {
}
