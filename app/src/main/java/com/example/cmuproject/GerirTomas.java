package com.example.cmuproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmuproject.Adapters.TomaAdapter;
import com.example.cmuproject.model.Medicamento;
import com.example.cmuproject.model.MedicamentosViewModel;
import com.example.cmuproject.model.PendentToma;
import com.example.cmuproject.model.Toma;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class GerirTomas extends Fragment {

    private OnListFragmentInteractionListener mListener;
    private MedicamentosViewModel medicamentosViewModel;
    private RecyclerView rc;
    private TomaAdapter tomaAdapter;
    private List<PendentToma> pententTomas;
    private List<Medicamento> myMedicamentos;

    public GerirTomas() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medicamentosViewModel = new ViewModelProvider(getActivity()).get(MedicamentosViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_toma_list, container, false);
        rc = view.findViewById(R.id.tomaRecyclerView);

        rc.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rc.addItemDecoration(itemDecoration);
        tomaAdapter = new TomaAdapter();
        rc.setAdapter(tomaAdapter);


        String pattern = "dd/MM/yyyy";
        String dateInString = new SimpleDateFormat(pattern).format(new Date());
        medicamentosViewModel.getallMedicamentos().observe(getActivity(), new Observer<List<Medicamento>>() {
            @Override
            public void onChanged(List<Medicamento> medicamentos) {
                myMedicamentos = medicamentos;
            }
        });
        medicamentosViewModel.getAllTomas().observe(getActivity(), new Observer<List<Toma>>() {
            @Override
            public void onChanged(List<Toma> tomas) {
                for (int i = 0; i < tomas.size(); i++) {
                    System.out.println(tomas.get(i).toString());
                }
            }
        });

        medicamentosViewModel.getPendentTomas().observe(getActivity(), new Observer<List<PendentToma>>() {
            @Override
            public void onChanged(List<PendentToma> pendentTomas) {

                pententTomas = pendentTomas;
                tomaAdapter.setmTomas(pendentTomas);
            }
        });
        medicamentosViewModel.getTodayTomas(dateInString).observe(getActivity(), new Observer<List<Toma>>() {
            @Override
            public void onChanged(List<Toma> tomas) {
                System.out.println("today tomas");
                for (int i = 0; i < tomas.size(); i++) {
                    System.out.println(tomas.get(i).toString());
                }
                createTomas(tomas);
            }
        });


        return view;
    }


    public void createTomas(List<Toma> tomas) {

        pententTomas = new ArrayList<>();

        Date calendar = Calendar.getInstance().getTime();
        String tempDate = calendar.toString();
        String[] myDate = tempDate.split(" ");
        String todayIs = "";

        switch (myDate[0]) {
            case "Mon":
                todayIs = "Segunda";
                break;
            case "Tue":
                todayIs = "Terça";
                break;
            case "Wed":
                todayIs = "Quarta";
                break;
            case "Thu":
                todayIs = "Quinta";
                break;
            case "Fri":
                todayIs = "Sexta";
                break;
            case "Sat":
                todayIs = "Sabado";
                break;
            case "Sun":
                todayIs = "Domingo";
                break;
        }

        for (int i = 0; i < myMedicamentos.size(); i++) {

            String tempDays = myMedicamentos.get(i).days;
            tempDays = tempDays.replace(" ", "");
            tempDays = tempDays.substring(1, tempDays.length() - 1);
            String[] thisDays = tempDays.split(",");

            for (int j = 0; j < thisDays.length; j++) {
                if (thisDays[j].equals(todayIs)) {
                    //verificar se ja foi tomado
                    String tempAlturas = myMedicamentos.get(i).alturas;
                    tempAlturas = tempAlturas.replace(" ", "");
                    tempAlturas = tempAlturas.substring(1, tempAlturas.length() - 1);
                    String[] thisAlturas = tempAlturas.split(",");
                    for (int k = 0; k < thisAlturas.length; k++) {
                        String theAltura = "";
                        Boolean found = false;
                        for (int i2 = 0; i2 < tomas.size() && !found; i2++) {
                            if (myMedicamentos.get(i).name.equals(tomas.get(i2).medicamentoName)) {
                                theAltura = getAltura(tomas.get(i2).hora);

                                if (theAltura.equals(thisAlturas[k])) {
                                    found = true;
                                    break;
                                }
                            }
                        }
                        if (!found) {
                            System.out.println("adicionou");
                            pententTomas.add(new PendentToma(thisAlturas[k], myMedicamentos.get(i).name));
                        }
                    }
                    break;
                }
            }
        }
        System.out.println("tamanho " + pententTomas.size());
        medicamentosViewModel.setPendentTomas(pententTomas);


    }

    private String getAltura(String hora) {

        String[] tempHora = hora.split(":");

        int thehora = Integer.parseInt(tempHora[0]);
        if (thehora > 6 && thehora < 12) {
            return "Manha";
        } else if (thehora >= 12 && thehora < 14) {
            return "Almoço";
        } else if (thehora >= 14 && thehora < 20) {
            return "Tarde";
        } else if (thehora >= 20 && thehora < 23) {
            return "Jantar";
        } else {
            return "Noite";
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //   mListener = (OnListFragmentInteractionListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnListFragmentInteractionListener {
        void onGTinteraction(Uri uri);
    }
}
