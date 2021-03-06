/**
 *  Copyright 2012-2014 Gunnar Morling (http://www.gunnarmorling.de/)
 *  and/or other contributors as indicated by the @authors tag. See the
 *  copyright.txt file in the distribution for a full listing of all
 *  contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mapstruct.ap.model;

import java.util.Set;

import org.mapstruct.ap.model.common.Parameter;
import org.mapstruct.ap.model.common.Type;
import org.mapstruct.ap.model.source.Method;
import org.mapstruct.ap.util.Strings;

/**
 * A {@link MappingMethod} implemented by a {@link Mapper} class which maps one {@code Map} type to another. Keys and
 * values are mapped either by a {@link TypeConversion} or another mapping method if required.
 *
 * @author Gunnar Morling
 */
public class MapMappingMethod extends MappingMethod {

    private final MethodReference keyMappingMethod;
    private final MethodReference valueMappingMethod;
    private final TypeConversion keyConversion;
    private final TypeConversion valueConversion;
    private final MethodReference factoryMethod;

    public MapMappingMethod(Method method, MethodReference keyMappingMethod, TypeConversion keyConversion,
                            MethodReference valueMappingMethod, TypeConversion valueConversion,
                            MethodReference factoryMethod) {
        super( method );

        this.keyMappingMethod = keyMappingMethod;
        this.keyConversion = keyConversion;
        this.valueMappingMethod = valueMappingMethod;
        this.valueConversion = valueConversion;
        this.factoryMethod = factoryMethod;
    }

    public Parameter getSourceParameter() {
        for ( Parameter parameter : getParameters() ) {
            if ( !parameter.isMappingTarget() ) {
                return parameter;
            }
        }

        throw new IllegalStateException( "Method " + this + " has no source parameter." );
    }

    public MethodReference getKeyMappingMethod() {
        return keyMappingMethod;
    }

    public TypeConversion getKeyConversion() {
        return keyConversion;
    }

    public MethodReference getValueMappingMethod() {
        return valueMappingMethod;
    }

    public TypeConversion getValueConversion() {
        return valueConversion;
    }

    @Override
    public Set<Type> getImportTypes() {
        Set<Type> types = super.getImportTypes();

        if ( valueConversion != null ) {
            types.addAll( valueConversion.getImportTypes() );
        }
        if ( keyConversion != null ) {
            types.addAll( keyConversion.getImportTypes() );
        }

        return types;
    }

    public String getKeyVariableName() {
        return Strings.getSaveVariableName(
            "key",
            getParameterNames()
        );
    }

    public String getValueVariableName() {
        return Strings.getSaveVariableName(
            "value",
            getParameterNames()
        );
    }

    public String getEntryVariableName() {
        return Strings.getSaveVariableName(
            "entry",
            getParameterNames()
        );
    }

    public MethodReference getFactoryMethod() {
        return this.factoryMethod;
    }
}
