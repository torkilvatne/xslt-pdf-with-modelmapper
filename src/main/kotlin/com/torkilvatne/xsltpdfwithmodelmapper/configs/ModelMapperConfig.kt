package com.torkilvatne.xsltpdfwithmodelmapper.configs

import org.modelmapper.Converter
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfig(
    private val konverterere: List<Converter<*, *>>,
    private val propertyMap: List<TypeMapConfigurer<*, *>>
) {
    @Bean
    fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.isSkipNullEnabled = true
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        modelMapper.configuration.fieldAccessLevel = org.modelmapper.config.Configuration.AccessLevel.PRIVATE
        modelMapper.configuration.isFieldMatchingEnabled = true
        configure(modelMapper)
        return modelMapper
    }

    private fun configure(modelMapper: ModelMapper) {
        konverterere.forEach { converter -> modelMapper.addConverter(converter) }
        propertyMap.forEach { typeMapConfigurer -> typeMapConfigurer.configureImpl(modelMapper) }
    }
}