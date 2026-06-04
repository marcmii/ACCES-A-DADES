require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');

const app = express();
const port = process.env.PORT || 3021;

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Connexió a local MongoDB
mongoose.connect(process.env.MONGO_URI)
  .then(() => console.log('Conectat a MongoDB: Reserves'))
  .catch(err => console.error('Error al conectarse:', err));

// Model de dades
const reservaSchema = new mongoose.Schema({
  dataReserva: { type: Date, required: true },
  clientNom: { type: String, required: true },
  restaurantNom: { type: String, required: true },
  nombrePersones: { type: Number, required: true },
  estat: { type: String, enum: ['confirmada', 'pendent', 'cancel·lada'], required: true }
});

const Reserva = mongoose.model('Reserva', reservaSchema, 'reserves');

// Ruta arrel
app.get('/', (req, res) => {
  res.send('Restaurant Reservations API is running!');
});

// Obtenir totes les reserves
app.get('/list', async (req, res) => {
  try {
    const reserves = await Reserva.find().sort({ dataReserva: 1 });
    res.status(200).json({ count: reserves.length, reserves });
  } catch (err) {
    res.status(500).json({ message: 'Error fetching reservations', error: err.message });
  }
});

// Afegir una nova reserva
app.post('/add', async (req, res) => {
  try {
    const { dataReserva, clientNom, restaurantNom, nombrePersones, estat } = req.body;
    if (!dataReserva || !clientNom || !restaurantNom || !nombrePersones || !estat) {
      return res.status(400).json({ message: 'Missing required fields' });
    }
    const newReserva = new Reserva({
      dataReserva: new Date(dataReserva),
      clientNom,
      restaurantNom,
      nombrePersones,
      estat
    });
    const savedReserva = await newReserva.save();
    res.status(201).json({ message: 'Reservation added successfully', reservation: savedReserva });
  } catch (err) {
    res.status(500).json({ message: 'Error adding reservation', error: err.message });
  }
});

// Obtenir reserves entre dates
app.get('/list/:dataini/:datafi', async (req, res) => {
  try {
    const { dataini, datafi } = req.params;
    const dateIni = new Date(dataini);
    const dateFi = new Date(datafi);
    dateFi.setHours(23, 59, 59, 999);

    if (isNaN(dateIni.getTime()) || isNaN(dateFi.getTime()) || dateIni > dateFi) {
      return res.status(400).json({ message: 'Invalid date range. Use YYYY-MM-DD' });
    }

    const reserves = await Reserva.find({
      dataReserva: { $gte: dateIni, $lte: dateFi }
    }).sort({ dataReserva: 1 });

    if (reserves.length === 0) {
      return res.status(404).json({ message: 'No reservations found in this date range' });
    }

    res.status(200).json({ count: reserves.length, dateRange: { from: dataini, to: datafi }, reserves });
  } catch (err) {
    res.status(500).json({ message: 'Error fetching reservations by date', error: err.message });
  }
});

// **Nou endpoint DELETE**: eliminar reserva per ID
app.delete('/delete/:id', async (req, res) => {
  try {
    const { id } = req.params;
    const deleted = await Reserva.findByIdAndDelete(id);
    if (!deleted) return res.status(404).json({ message: 'No s’ha trobat la reserva' });
    res.status(200).json({ message: 'Reserva eliminada correctament' });
  } catch (err) {
    res.status(500).json({ message: 'Error eliminant reserva', error: err.message });
  }
});

// Iniciar servidor
app.listen(port, '0.0.0.0', () => {
  console.log(`Restaurant API running on http://localhost:${port}`);
});