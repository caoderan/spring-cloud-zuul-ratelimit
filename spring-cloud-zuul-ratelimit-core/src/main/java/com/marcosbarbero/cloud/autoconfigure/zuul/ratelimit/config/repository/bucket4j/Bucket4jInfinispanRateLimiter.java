/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.bucket4j;

import io.github.bucket4j.grid.GridBucketState;
import io.github.bucket4j.grid.ProxyManager;
import io.github.bucket4j.grid.infinispan.Infinispan;
import io.github.bucket4j.grid.infinispan.InfinispanBucketBuilder;
import org.infinispan.functional.FunctionalMap.ReadWriteMap;

/**
 * Bucket4j rate limiter configuration.
 *
 * @author Liel Chayoun
 * @since 2018-04-06
 */
public class Bucket4jInfinispanRateLimiter extends AbstractBucket4jRateLimiter<InfinispanBucketBuilder, Infinispan> {

	private final ReadWriteMap<String, GridBucketState> readWriteMap;

	public Bucket4jInfinispanRateLimiter(final ReadWriteMap<String, GridBucketState> readWriteMap) {
		super(io.github.bucket4j.grid.infinispan.Infinispan.class);
		this.readWriteMap = readWriteMap;
		super.init();
	}

	@Override
	protected ProxyManager<String> getProxyManager(io.github.bucket4j.grid.infinispan.Infinispan extension) {
		return extension.proxyManagerForMap(readWriteMap);
	}
}
