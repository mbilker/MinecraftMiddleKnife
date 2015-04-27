/*
 * Copyright 2012 Robert 'Bobby' Zenz. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list
 * of conditions and the following disclaimer in the documentation and/or other materials
 * provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY Robert 'Bobby' Zenz ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL Robert 'Bobby' Zenz OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of Robert 'Bobby' Zenz.
 */
package org.bonsaimind.minecraftmiddleknife.post16;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple argument which is used to launch Minecraft.
 */
public final class Argument {
	private final String name;
	private final String value;
	
	public Argument(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
	/**
	 * Returns the string representation of this argument. That representation
	 * is {@code --name} if the {@code value} is null or empty, and
	 * {@code --name=value} for all other cases.
	 * 
	 * @return The string representation.
	 */
	@Override
	public String toString() {
		if (value == null || value.length() == 0) {
			return "--" + name;
		} else {
			return "--" + name + "=" + value;
		}
	}
	
	/**
	 * Converts the given arguments to strings.
	 * 
	 * @param arguments
	 * @return
	 */
	public static String[] toStrings(Argument... arguments) {
		List<String> argumentsAsStrings = new ArrayList<String>();
		
		for (Argument arg : arguments) {
			argumentsAsStrings.add(arg.toString());
		}
		
		return argumentsAsStrings.toArray(new String[argumentsAsStrings.size()]);
	}
}
