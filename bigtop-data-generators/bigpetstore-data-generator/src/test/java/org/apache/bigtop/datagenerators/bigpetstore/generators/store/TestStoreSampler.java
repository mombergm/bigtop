/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.bigtop.datagenerators.bigpetstore.generators.store;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.apache.bigtop.datagenerators.bigpetstore.datamodels.Store;
import org.apache.bigtop.datagenerators.bigpetstore.datamodels.inputs.ZipcodeRecord;
import org.apache.bigtop.datagenerators.bigpetstore.generators.store.StoreSampler;
import org.apache.bigtop.datagenerators.samplers.SeedFactory;
import org.apache.bigtop.datagenerators.samplers.samplers.RouletteWheelSampler;
import org.apache.bigtop.datagenerators.samplers.samplers.Sampler;
import org.apache.bigtop.datagenerators.samplers.samplers.SequenceSampler;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

public class TestStoreSampler
{

	@Test
	public void testSampler() throws Exception
	{
		Collection<ZipcodeRecord> zipcodes = Arrays.asList(new ZipcodeRecord[] {
				new ZipcodeRecord("11111", Pair.of(1.0, 1.0), "AZ", "Tempte", 30000.0, 100),
				new ZipcodeRecord("22222", Pair.of(2.0, 2.0), "AZ", "Phoenix", 45000.0, 200),
				new ZipcodeRecord("33333", Pair.of(3.0, 3.0), "AZ", "Flagstaff", 60000.0, 300)
				});

		SeedFactory factory = new SeedFactory(1234);

		Sampler<Store> sampler = new StoreSampler(new SequenceSampler(),
				RouletteWheelSampler.createUniform(zipcodes, factory));

		Store store = sampler.sample();
		assertNotNull(store);
		assertTrue(store.getId() >= 0);
		assertNotNull(store.getName());
		assertNotNull(store.getLocation());

	}

}
