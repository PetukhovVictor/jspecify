/*
 * Copyright 2020 The jspecify Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jspecify.annotations.DefaultNonNull;
import org.jspecify.annotations.Nullable;

@DefaultNonNull
public class SelfType<T extends SelfType<T>> {
  public void foo(T t) {}
}

class B extends SelfType<B> {}

@DefaultNotNull
class C<E extends C<E>> extends SelfType<E> {}

class AK extends SelfType<AK> {}

class AKN extends SelfType<@Nullable AK> {}

class BK extends B {}

class CK extends C<CK> {}

class CKN extends C<@Nullable CK> {
  public void main(AK ak, AKN akn, BK bk, CK ck, CKN ckn) {
    ak.foo(ak);
    // jspecify_nullness_mismatch
    ak.foo(null);

    // jspecify_nullness_mismatch
    akn.foo(null);

    bk.foo(bk);
    // jspecify_nullness_mismatch
    bk.foo(null);

    ck.foo(ck);
    // jspecify_nullness_mismatch
    ck.foo(null);

    // jspecify_nullness_mismatch
    ckn.foo(null);
  }
}