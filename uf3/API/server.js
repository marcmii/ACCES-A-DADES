require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');

const app = express();
const port = process.env.PORT || 3021;


app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Conexió a MongoDB Atlas
const uri = process.env.MONGO_URI;
console.log("URI:", uri);

mongoose.connect(uri)
  .then(() => console.log('Connected to MongoDB: Reserves'))
  .catch(err => console.error('Error connecting to MongoDB:', err));

/* =========================
   MODEL DE DADES: RESERVAS
   ========================= */

const reservaSchema = new mongoose.Schema({
  dataReserva: {
    type: Date,
    required: true
  },
  clientNom: {
    type: String,
    required: true
  },
  restaurantNom: {
    type: String,
    required: true
  },
  nombrePersones: {
    type: Number,
    required: true
  },
  estat: {
    type: String,
    enum: ['confirmada', 'pendent', 'cancel·lada'],
    required: true
  }
}, {
  collection: 'reserves',
  strict: false
});

const Reserva = mongoose.model('Reserva', reservaSchema, 'reserves');

/* =========================
   ENDPOINTS
   ========================= */

// Ruta arrel
app.get('/', (req, res) => {
  res.send('Restaurant Reservations API is running!');
});

/**
 * GET /list
 * Obtenir totes les reserves
 */
app.get('/list', async (req, res) => {
  try {
    const reserves = await Reserva.find().sort({ dataReserva: 1 });

    res.status(200).json({
      count: reserves.length,
      reserves: reserves
    });

    console.log(`Retrieved ${reserves.length} reservations`);
  } catch (err) {
    res.status(500).json({
      message: 'Error fetching reservations',
      error: err.message
    });
  }
});

/**
 * POST /add
 * Afegir una nova reserva
 */
app.post('/add', async (req, res) => {
  try {
    const {
      dataReserva,
      clientNom,
      restaurantNom,
      nombrePersones,
      estat
    } = req.body;

  
    if (!dataReserva || !clientNom || !restaurantNom || !nombrePersones || !estat) {
      return res.status(400).json({
        message: 'Missing required fields',
        required: ['dataReserva', 'clientNom', 'restaurantNom', 'nombrePersones', 'estat']
      });
    }

    const newReserva = new Reserva({
      dataReserva: new Date(dataReserva),
      clientNom,
      restaurantNom,
      nombrePersones,
      estat
    });

    const savedReserva = await newReserva.save();

    res.status(201).json({
      message: 'Reservation added successfully',
      reservation: savedReserva
    });

    console.log('New reservation added:', savedReserva._id);
  } catch (err) {
    res.status(500).json({
      message: 'Error adding reservation',
      error: err.message
    });
  }
});

/**
 * GET /list/:dataini/:datafi
 * Obtenir reserves entre dates
 * Ejemplo: /list/2024-12-01/2024-12-31
 */
app.get('/list/:dataini/:datafi', async (req, res) => {
  try {
    const { dataini, datafi } = req.params;

    const dateIni = new Date(dataini);
    const dateFi = new Date(datafi);
    dateFi.setHours(23, 59, 59, 999);

  
    if (isNaN(dateIni.getTime()) || isNaN(dateFi.getTime())) {
      return res.status(400).json({
        message: 'Invalid date format. Use YYYY-MM-DD',
        example: '/list/2024-12-01/2024-12-31'
      });
    }

    if (dateIni > dateFi) {
      return res.status(400).json({
        message: 'Initial date must be before final date'
      });
    }

    const reserves = await Reserva.find({
      dataReserva: {
        $gte: dateIni,
        $lte: dateFi
      }
    }).sort({ dataReserva: 1 });

    if (reserves.length === 0) {
      return res.status(404).json({
        message: 'No reservations found in this date range'
      });
    }

    res.status(200).json({
      count: reserves.length,
      dateRange: {
        from: dataini,
        to: datafi
      },
      reserves: reserves
    });

    console.log(`Found ${reserves.length} reservations`);
  } catch (err) {
    res.status(500).json({
      message: 'Error fetching reservations by date',
      error: err.message
    });
  }
});

/* =========================
   SERVER
   ========================= */

app.listen(port, '0.0.0.0', () => {
  console.log(`Restaurant API running on http://localhost:${port}`);
  console.log(`Available endpoints:`);
  console.log(`  GET  /`);
  console.log(`  GET  /list`);
  console.log(`  POST /add`);
  console.log(`  GET  /list/:dataini/:datafi`);
});
