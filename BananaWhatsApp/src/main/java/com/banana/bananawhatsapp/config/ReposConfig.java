package com.banana.bananawhatsapp.config;

import com.banana.bananawhatsapp.persistencia.IMensajeRepository;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import com.banana.bananawhatsapp.persistencia.MensajeDBRepository;
import com.banana.bananawhatsapp.persistencia.UsuarioDBRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReposConfig {
    @Value("${db_url}")
    String db_url;

    @Bean
    public IUsuarioRepository createIUsuarioRepo() {
        UsuarioDBRepository repo = new UsuarioDBRepository();
        repo.setDb_url(db_url);
        return repo;
    }
    @Bean
    public IMensajeRepository createIMensajeRepository() {
        MensajeDBRepository repo = new MensajeDBRepository();
        repo.setDb_url(db_url);
        return repo;
    }
}
