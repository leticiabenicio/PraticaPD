package com.gugawag.pdist.servlets;

import com.gugawag.pdist.ejbs.MensagemService;
import com.gugawag.pdist.model.Mensagem;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/mensagem.do"})
public class MensagemServlet extends javax.servlet.http.HttpServlet {

    @EJB(lookup = "java:module/mensagemService")
    private MensagemService mensagemService;

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        PrintWriter resposta = response.getWriter();

        for (Mensagem mensagem : mensagemService.listar()) {
            resposta.println(mensagem.getMensagem());
        }

    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        long id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("mensagem");
        mensagemService.inserir(id, nome);

    }
}
