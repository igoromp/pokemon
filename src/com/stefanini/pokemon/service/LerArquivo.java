package com.stefanini.pokemon.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;

public class LerArquivo<T> {
	
	public T[] jsonToClasse(String path, Class<?> classe) throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		Gson gson = new Gson();
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(classLoader.getResource(path).getPath()));
			return (T[]) gson.fromJson(bufferedReader, classe);
		} catch (FileNotFoundException e) {
			throw new Exception("Erro ao ler arquivo Json", e);
		}
	}
}
