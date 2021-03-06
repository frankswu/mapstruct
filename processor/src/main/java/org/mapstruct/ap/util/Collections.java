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
package org.mapstruct.ap.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Provides utility methods around collections.
 *
 * @author Gunnar Morling
 */
public class Collections {

    private Collections() {
    }

    public static <T> Set<T> asSet(T... elements) {
        Set<T> set = new HashSet<T>();

        for ( T element : elements ) {
            set.add( element );
        }

        return set;
    }

    public static <T> Set<T> asSet(Collection<T> collection, T... elements) {
        Set<T> set = new HashSet<T>( collection );

        for ( T element : elements ) {
            set.add( element );
        }

        return set;
    }
}
